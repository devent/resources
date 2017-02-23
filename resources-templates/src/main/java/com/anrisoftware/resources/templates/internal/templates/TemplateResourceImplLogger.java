/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.templates.internal.templates;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link TemplateResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplateResourceImplLogger extends AbstractLogger {

    private static final String INVALIDATE_RESOURCE = "Invalidate resource {}.";

    private static final String PROCESS_TEMPLATE = "Process the template for {}.";

    /**
     * Creates a logger for {@link TemplateResourceImpl}.
     * 
     * @deprecated public scope needed for serialization.
     */
    @Deprecated
    public TemplateResourceImplLogger() {
        super(TemplateResourceImpl.class);
    }

    void processTemplate(TemplateResourceImpl resource) {
        log.debug(PROCESS_TEMPLATE, resource);
    }

    void resourceInvalidated(TemplateResourceImpl resource) {
        log.debug(INVALIDATE_RESOURCE, resource);
    }

}
