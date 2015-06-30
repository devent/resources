/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.resource;

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

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.images.api.ImageResolution;
import com.anrisoftware.resources.images.api.ImageResource;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Image resource with lazy loading. Two image resources are equals if the
 * resource URL is the same.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
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
    ImageResourceImpl(ImageResourceImplLogger logger, @Assisted String name,
            @Assisted Locale locale, @Assisted ImageResolution resolution,
            @Assisted URL url) {
        this(logger, name, locale, resolution, url, null);
    }

    @AssistedInject
    ImageResourceImpl(ImageResourceImplLogger logger, @Assisted String name,
            @Assisted Locale locale, @Assisted ImageResolution resolution,
            @Assisted Image image) {
        this(logger, name, locale, resolution, null, image);
    }

    private ImageResourceImpl(ImageResourceImplLogger logger, String name,
            Locale locale, ImageResolution resolution, URL url, Image image) {
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
    public synchronized Image getImage() throws ResourcesException {
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
    public Image getImage(ImageObserver observer) throws ResourcesException {
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
    public synchronized BufferedImage getBufferedImage(int imageType)
            throws ResourcesException {
        if (bufferedImage == null) {
            BufferedImage image = imageBufferedWorker.toBuffered(getImage(),
                    getWidthPx(), getHeightPx(), imageType);
            this.bufferedImage = image;
            this.image = null;
        }
        return bufferedImage;
    }

    @Override
    public BufferedImage getBufferedImage(int imageType, ImageObserver observer)
            throws ResourcesException {
        if (bufferedImage == null) {
            BufferedImage image = imageBufferedWorker.toBuffered(
                    getImage(observer), getWidthPx(observer),
                    getHeightPx(observer), imageType);
            this.bufferedImage = image;
            this.image = null;
        }
        return bufferedImage;
    }

    @Override
    public synchronized int getHeightPx() throws ResourcesException {
        if (size.height == HEIGHT_WIDTH_NOT_SET) {
            size.height = determineHeight();
        }
        return size.height;
    }

    private Integer determineHeight() throws ResourcesException {
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
    public int getHeightPx(ImageObserver observer) throws ResourcesException {
        int height = getImage(observer).getHeight(observer);
        size.height = height;
        return height;
    }

    @Override
    public synchronized int getWidthPx() throws ResourcesException {
        if (size.width == HEIGHT_WIDTH_NOT_SET) {
            size.width = determineWidth();
        }
        return size.width;
    }

    private int determineWidth() throws ResourcesException {
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
    public int getWidthPx(ImageObserver observer) throws ResourcesException {
        int width = getImage(observer).getWidth(observer);
        size.width = width;
        return width;
    }

    @Override
    public synchronized Dimension getSizePx() throws ResourcesException {
        if (size.height == HEIGHT_WIDTH_NOT_SET) {
            getHeightPx();
        }
        if (size.width == HEIGHT_WIDTH_NOT_SET) {
            getWidthPx();
        }
        return size;
    }

    @Override
    public Dimension getSizePx(ImageObserver observer)
            throws ResourcesException {
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
        return new ToStringBuilder(this).append(name).append(locale)
                .append(resolution).toString();
    }

}
