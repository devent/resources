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
package com.anrisoftware.resources.api.external;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

import org.apache.commons.lang3.exception.DefaultExceptionContext;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Exception that is thrown if there was an error loading a resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ResourcesException extends MissingResourceException {

    private final ExceptionContext context;

    private final Throwable cause;

    /**
     * @see MissingResourceException#MissingResourceException(String, String,
     *      String)
     *
     * @since 1.7
     */
    public ResourcesException(String message, String className, String key) {
        this(null, message, className, key);
    }

    /**
     * @param cause
     *            the {@link Throwable} cause of the exception.
     *
     * @see MissingResourceException#MissingResourceException(String, String,
     *      String)
     *
     * @since 1.7
     */
    public ResourcesException(Throwable cause, String message, String className,
            String key) {
        super(message, className, key);
        this.context = new DefaultExceptionContext();
        this.cause = cause;
        if (cause != null) {
            addContext("cause", cause);
        }
        if (!isEmpty(key)) {
            addContext("key", key);
        }
        if (!isEmpty(className)) {
            addContext("class name", className);
        }
    }

    /**
     * Adds the context with the specified name.
     *
     * @param name
     *            the name of the context.
     *
     * @param value
     *            the context value.
     *
     * @return the context {@link Exception}.
     *
     * @since 1.7
     */
    public ResourcesException addContext(String name, Object value) {
        context.addContextValue(name, value);
        return this;
    }

    /**
     * Returns the context of the exception.
     *
     * @return an unmodifiable {@link Map} with the context.
     *
     * @since 2.1
     */
    public List<Pair<String, Object>> getContext() {
        return context.getContextEntries();
    }

    @Override
    public synchronized Throwable getCause() {
        return (cause == this ? null : cause);
    }

    @Override
    public String getMessage() {
        return getFormattedExceptionMessage(super.getMessage());
    }

    public String getRawMessage() {
        return super.getMessage();
    }

    public String getFormattedExceptionMessage(final String baseMessage) {
        return context.getFormattedExceptionMessage(baseMessage);
    }

}
