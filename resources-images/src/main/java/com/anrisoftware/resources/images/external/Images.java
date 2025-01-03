/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.images.external;

import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.api.external.ResourcesException;

public interface Images {

    /**
     * Returns the used resource bundle base name.
     * 
     * @return the used base name {@link String}.
     * 
     * @since 1.1
     */
    String getBaseName();

    /**
     * Returns the used resource bundle class loader.
     * 
     * @return the used {@link ClassLoader}.
     * 
     * @since 1.1
     */
    ClassLoader getClassLoader();

    /**
     * Returns the used resource bundle control.
     * 
     * @return the used {@link ResourceBundle.Control}.
     * 
     * @since 1.1
     */
    ResourceBundle.Control getControl();

    /**
     * Returns the image with the specified name, locale size.
     * <p>
     * If the needed size is not the size of the image, a scaled instance will be
     * returned. The most nearest available image resolution will be used for
     * scaling.
     * 
     * @param name   the name {@link String} of the resource.
     * 
     * @param locale the {@link Locale} of the resource.
     * 
     * @param sizePx the needed {@link Dimension} width and height of the image in
     *               pixels.
     * 
     * @return {@link ImageResource}
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    ImageResource getResource(String name, Locale locale, Dimension sizePx);

    /**
     * Returns the image with the specified name, locale size.
     * <p>
     * If the needed size is not the size of the image, a scaled instance will be
     * returned. The most nearest available image resolution will be used for
     * scaling.
     * 
     * @param name   the name {@link String} of the resource.
     * 
     * @param locale the {@link Locale} of the resource.
     * 
     * @param size   the needed {@link IconSize} of the image.
     * 
     * @return {@link ImageResource}
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    ImageResource getResource(String name, Locale locale, IconSize size);

    /**
     * Returns the image with the specified name, locale size.
     * <p>
     * If the needed size is not the size of the image, a scaled instance will be
     * returned. The most nearest available image resolution will be used for
     * scaling.
     * 
     * @param name     the name {@link String} of the resource.
     * 
     * @param locale   the {@link Locale} of the resource.
     * 
     * @param widthPx  the needed width of the image in pixels.
     * 
     * @param heightPx the needed height of the image in pixels.
     * 
     * @return {@link ImageResource}
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    ImageResource getResource(String name, Locale locale, int widthPx, int heightPx);

    /**
     * Returns the image with the given name, locale, size and resolution.
     * <p>
     * If the needed size is not the size of the image, a scaled instance will be
     * returned, from the specified resolution.
     * 
     * @param name       the name {@link String} of the resource.
     * 
     * @param locale     the {@link Locale} of the resource.
     * 
     * @param sizePx     the needed {@link Dimension} width and height of the image
     *                   in pixels.
     * 
     * @param resolution the {@link ImageResolution} to use.
     * 
     * @return {@link ImageResource}
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    ImageResource getResource(String name, Locale locale, Dimension sizePx, ImageResolution resolution);

    /**
     * Returns the image with the given name, locale, size and resolution.
     * <p>
     * If the needed size is not the size of the image, a scaled instance will be
     * returned, from the specified resolution.
     * 
     * @param name       the name {@link String} of the resource.
     * 
     * @param locale     the {@link Locale} of the resource.
     * 
     * @param widthPx    the needed width of the image in pixels.
     * 
     * @param heightPx   the needed height of the image in pixels.
     * 
     * @param resolution the {@link ImageResolution} to use.
     * 
     * @return {@link ImageResource}
     * 
     * @throws ResourcesException if the resource is not available.
     * 
     * @since 1.2
     */
    ImageResource getResource(String name, Locale locale, int widthPx, int heightPx, ImageResolution resolution);

}
