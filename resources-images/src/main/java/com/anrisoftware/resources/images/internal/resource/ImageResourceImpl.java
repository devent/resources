/*
 * Copyright 2012-2021 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.images.internal.resource;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.resources.images.external.ImageResolution;
import com.anrisoftware.resources.images.external.ImageResource;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

class ImageResourceImpl implements ImageResource {

    private static final int HEIGHT_WIDTH_NOT_SET = -1;

    private final ImageResourceImplLogger log;

    private final String name;

    private final Locale locale;

    private final Dimension size;

    private final ImageResolution resolution;

    private final URL url;

    @Inject
    private ImageLoadWorker imageLoadWorker;

    @Inject
    private ImageGetterWorker imageGetterWorker;

    @Inject
    private ImageBufferedWorker imageBufferedWorker;

    private Image image;

    private BufferedImage bufferedImage;

    private boolean imageLoaded;

    @AssistedInject
    ImageResourceImpl(ImageResourceImplLogger logger, @Assisted String name, @Assisted Locale locale,
            @Assisted ImageResolution resolution, @Assisted URL url) {
        this(logger, name, locale, resolution, url, null);
    }

    @AssistedInject
    ImageResourceImpl(ImageResourceImplLogger logger, @Assisted String name, @Assisted Locale locale,
            @Assisted ImageResolution resolution, @Assisted Image image) {
        this(logger, name, locale, resolution, null, image);
    }

    private ImageResourceImpl(ImageResourceImplLogger logger, String name, Locale locale, ImageResolution resolution,
            URL url, Image image) {
        this.log = logger;
        this.name = name;
        this.locale = locale;
        this.resolution = resolution;
        this.url = url;
        this.image = image;
        this.size = new Dimension(HEIGHT_WIDTH_NOT_SET, HEIGHT_WIDTH_NOT_SET);
        this.imageLoaded = image != null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public ImageResolution getResolution() {
        return resolution;
    }

    @Override
    public synchronized Image getImage() {
        if (!imageLoaded) {
            Image image;
            image = imageLoadWorker.loadImage(this, log, url);
            ImageIcon loader;
            loader = imageGetterWorker.getImage(this, log, image, null);
            image = loader.getImage();
            imageLoaded = true;
            this.image = image;
            return image;
        }
        if (bufferedImage != null) {
            return bufferedImage;
        } else {
            return image;
        }
    }

    @Override
    public Image getImage(ImageObserver observer) {
        if (!imageLoaded) {
            Image image;
            image = imageLoadWorker.loadImage(this, log, url);
            ImageIcon loader;
            loader = imageGetterWorker.getImage(this, log, image, observer);
            image = loader.getImage();
            imageLoaded = true;
            this.image = image;
            return image;
        }
        if (bufferedImage != null) {
            return bufferedImage;
        } else {
            return image;
        }
    }

    @Override
    public synchronized BufferedImage getBufferedImage(int imageType) {
        if (bufferedImage == null) {
            BufferedImage image = imageBufferedWorker.toBuffered(getImage(), getWidthPx(), getHeightPx(), imageType);
            this.bufferedImage = image;
            this.image = null;
        }
        return bufferedImage;
    }

    @Override
    public BufferedImage getBufferedImage(int imageType, ImageObserver observer) {
        if (bufferedImage == null) {
            BufferedImage image = imageBufferedWorker.toBuffered(getImage(observer), getWidthPx(observer),
                    getHeightPx(observer), imageType);
            this.bufferedImage = image;
            this.image = null;
        }
        return bufferedImage;
    }

    @Override
    public synchronized int getHeightPx() {
        if (size.height == HEIGHT_WIDTH_NOT_SET) {
            size.height = determineHeight();
        }
        return size.height;
    }

    private Integer determineHeight() {
        ImageResourceObserver observer = new ImageResourceObserver(HEIGHT);
        int height = getImage().getHeight(observer);
        height = waitForHeight(observer, height);
        return height;
    }

    private int waitForHeight(ImageResourceObserver observer, int height) {
        if (height < 0) {
            log.waitForHeight(this);
            while (!observer.isDone()) {
                Thread.yield();
            }
            height = observer.getHeight();
        }
        return height;
    }

    @Override
    public int getHeightPx(ImageObserver observer) {
        int height = getImage(observer).getHeight(observer);
        size.height = height;
        return height;
    }

    @Override
    public synchronized int getWidthPx() {
        if (size.width == HEIGHT_WIDTH_NOT_SET) {
            size.width = determineWidth();
        }
        return size.width;
    }

    private int determineWidth() {
        ImageResourceObserver observer = new ImageResourceObserver(WIDTH);
        int width = getImage().getWidth(observer);
        width = waitForWidth(observer, width);
        return width;
    }

    private int waitForWidth(ImageResourceObserver observer, int width) {
        if (width < 0) {
            log.waitForWidth(this);
            while (!observer.isDone()) {
                Thread.yield();
            }
            width = observer.getWidth();
        }
        return width;
    }

    @Override
    public int getWidthPx(ImageObserver observer) {
        int width = getImage(observer).getWidth(observer);
        size.width = width;
        return width;
    }

    @Override
    public synchronized Dimension getSizePx() {
        if (size.height == HEIGHT_WIDTH_NOT_SET) {
            getHeightPx();
        }
        if (size.width == HEIGHT_WIDTH_NOT_SET) {
            getWidthPx();
        }
        return size;
    }

    @Override
    public Dimension getSizePx(ImageObserver observer) {
        if (size.height == HEIGHT_WIDTH_NOT_SET) {
            getHeightPx(observer);
        }
        if (size.width == HEIGHT_WIDTH_NOT_SET) {
            getWidthPx(observer);
        }
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(name).append(locale).append(resolution).toString();
    }

}
