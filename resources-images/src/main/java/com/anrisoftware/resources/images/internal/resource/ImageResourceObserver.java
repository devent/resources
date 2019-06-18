/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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

import java.awt.Image;
import java.awt.image.ImageObserver;

class ImageResourceObserver implements ImageObserver {

    private final int flags;

    private int x;

    private int y;

    private int imageWidth;

    private int imaheHeight;

    private boolean done;

    /**
     * Sets the flags we observe for.
     * 
     * @param flags one or a combination of the flags of the image observer.
     * 
     * @see ImageObserver
     */
    public ImageResourceObserver(int flags) {
        this.flags = flags;
        this.done = false;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if ((infoflags & flags) == flags) {
            this.done = true;
            this.x = x;
            this.y = y;
            this.imageWidth = width;
            this.imaheHeight = height;
        }
        return false;
    }

    /**
     * Returns <code>true</code> if the given flags is available from the image.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the height of the image, if the {@link ImageObserver#HEIGHT} flag
     * becomes available.
     */
    public int getHeight() {
        return imaheHeight;
    }

    /**
     * Returns the width of the image, if the {@link ImageObserver#WIDTH} flag
     * becomes available.
     */
    public int getWidth() {
        return imageWidth;
    }

    /**
     * Returns the X-axis of the pixels, if the {@link ImageObserver#SOMEBITS} flag
     * becomes available.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y-axis of the pixels, if the {@link ImageObserver#SOMEBITS} flag
     * becomes available.
     */
    public int getY() {
        return y;
    }
}
