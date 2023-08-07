/*
 * Copyright 2012-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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

import java.awt.Image;
import java.net.URL;
import java.util.Locale;

public interface ImageResourceFactory {

    /**
     * Creates a new image resource that will load an image from the specified URL.
     * 
     * @param name       the name {@link String} of this resource.
     * 
     * @param locale     the {@link Locale} this text resource.
     * 
     * @param resolution the {@link ImageResolution} of the image.
     * 
     * @param url        the resource {@link URL} of the image.
     * 
     * @return {@link ImageResource}
     * 
     * @since 1.1
     */
    ImageResource create(String name, Locale locale, ImageResolution resolution, URL url);

    /**
     * Creates a new image resource that have an already loaded image.
     * 
     * @param name       the name {@link String} of this resource.
     * 
     * @param locale     the {@link Locale} this text resource.
     * 
     * @param resolution the {@link ImageResolution} of the image.
     * 
     * @param image      the {@link Image} of the resource.
     *
     * @return {@link ImageResource}
     * 
     * @since 1.1
     */
    ImageResource create(String name, Locale locale, ImageResolution resolution, Image image);
}
