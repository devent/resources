/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.templates.internal.worker;

import static com.anrisoftware.resources.templates.internal.worker.STTemplateWorkerFactory.DELIMITER_START_CHAR_PROPERTY;
import static com.anrisoftware.resources.templates.internal.worker.STTemplateWorkerFactory.DELIMITER_STOP_CHAR_PROPERTY;
import static com.anrisoftware.resources.templates.internal.worker.STTemplateWorkerFactory.ENCODING_PROPERTY;
import static com.anrisoftware.resources.templates.internal.worker.STTemplateWorkerFactory.IMPORTS_KEY;
import static com.anrisoftware.resources.templates.internal.worker.STTemplateWorkerFactory.RENDERERS_KEY;
import static org.apache.commons.lang3.ArrayUtils.remove;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.STMessage;

import com.anrisoftware.propertiesutils.ContextProperties;
import com.anrisoftware.resources.external.ResourcesException;
import com.anrisoftware.resources.templates.external.AttributeRenderer;
import com.anrisoftware.resources.templates.external.SerializiableGroup;
import com.anrisoftware.resources.templates.external.TemplateWorker;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Template worker that is using a <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class STTemplateWorker implements TemplateWorker {

    private final STTemplateWorkerLogger log;

    private final ContextProperties properties;

    private final URL templateUrl;

    private final Map<Serializable, Serializable> attributes;

    private transient STGroupFile groupFile;

    private transient ResourcesException error;

    /**
     * @see STTemplateWorkerFactory#create(URL, Properties, Map)
     */
    @Inject
    STTemplateWorker(STTemplateWorkerLogger logger, @Assisted URL templateUrl,
            @Assisted Properties properties,
            @Assisted Map<Serializable, Serializable> attributes)
            throws ResourcesException {
        this.log = logger;
        this.templateUrl = templateUrl;
        this.properties = new ContextProperties(this, properties);
        this.attributes = attributes;
        createGroupFile();
    }

    private Object readResolve() throws ObjectStreamException {
        createGroupFile();
        return this;
    }

    private void createGroupFile() {
        this.groupFile = openGroupFile(templateUrl);
        setupRenderers(groupFile);
        setupImports(groupFile);
        groupFile.setListener(new STErrorListener() {

            @Override
            public void runTimeError(STMessage msg) {
                error = log.errorProcessTemplate(msg, templateUrl);
            }

            @Override
            public void internalError(STMessage msg) {
                error = log.errorProcessTemplate(msg, templateUrl);
            }

            @Override
            public void compileTimeError(STMessage msg) {
                error = log.errorProcessTemplate(msg, templateUrl);
            }

            @Override
            public void IOError(STMessage msg) {
                error = log.errorProcessTemplate(msg, templateUrl);
            }
        });
    }

    private void setupImports(STGroupFile group) {
        if (!attributes.containsKey(IMPORTS_KEY)) {
            return;
        }
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<Serializable> imports = (List) attributes.get(IMPORTS_KEY);
        for (Serializable s : imports) {
            if (s instanceof SerializiableGroup) {
                SerializiableGroup sgroup = (SerializiableGroup) s;
                group.importTemplates(sgroup.group);
            }
        }
    }

    private void setupRenderers(STGroupFile group) {
        if (!attributes.containsKey(RENDERERS_KEY)) {
            return;
        }
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<AttributeRenderer> renderers = (List) attributes
                .get(RENDERERS_KEY);
        for (AttributeRenderer renderer : renderers) {
            group.registerRenderer(renderer.getAttributeType(), renderer);
        }
    }

    private STGroupFile openGroupFile(URL templateUrl)
            throws ResourcesException {
        String encoding;
        char startChar;
        char stopChar;
        encoding = properties.getProperty(ENCODING_PROPERTY);
        startChar = properties.getCharProperty(DELIMITER_START_CHAR_PROPERTY);
        stopChar = properties.getCharProperty(DELIMITER_STOP_CHAR_PROPERTY);
        try {
            return new STGroupFile(templateUrl, encoding, startChar, stopChar);
        } catch (STException e) {
            throw log.errorOpenGroupFile(e);
        }
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public Map<Serializable, Serializable> getAttributes() {
        return attributes;
    }

    @Override
    public URL getURL() {
        return templateUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getTemplate() {
        return (T) groupFile;
    }

    /**
     * @param name
     *            the template group name and the template name if the
     *            conditions apply: a) the specified data array is empty or b)
     *            the data array contains only attribute name-value pairs.
     *
     * @param data
     *            the data array containing attribute-name-value pairs. The
     *            first element {@code data[0]} can contain the name of the
     *            template in the template group. If so, then the amount of
     *            elements in the array must be odd. If the amount of elements
     *            in the array is not odd then the data array does not contain
     *            the template name as the first element.
     */
    @Override
    public String process(String name, Object... data)
            throws ResourcesException {
        ST template = createTemplate(name, data);
        String rendered = renderTemplate(template);
        log.templateProcessed(name);
        return rendered;
    }

    private ST createTemplate(String templateName, Object... data)
            throws ResourcesException {
        templateName = getTemplateName(templateName, data);
        ST template = groupFile.getInstanceOf(templateName);
        log.checkTemplateCreated(template, templateName, templateUrl);
        throwErrors();
        setupTemplateAttributes(template, data);
        return template;
    }

    private void setupTemplateAttributes(ST template, Object[] data) {
        if (data.length % 2 != 0) {
            data = remove(data, 0);
        }
        for (int i = 0; i < data.length; i++) {
            template.add(data[i].toString(), data[++i]);
        }
    }

    private String getTemplateName(String templateName, Object[] data) {
        if (data.length == 0) {
            return templateName;
        } else if (data.length % 2 == 0) {
            return templateName;
        } else {
            return data[0].toString();
        }
    }

    private String renderTemplate(ST template) throws ResourcesException {
        String rendered = template.render();
        throwErrors();
        return rendered;
    }

    private void throwErrors() throws ResourcesException {
        if (error != null) {
            throw error;
        }
    }
}
