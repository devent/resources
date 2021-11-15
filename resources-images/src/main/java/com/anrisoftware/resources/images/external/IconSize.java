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
package com.anrisoftware.resources.images.external;

public enum IconSize {

    /**
     * Size 16x16.
     */
    SMALL(16),

    /**
     * Size 22x22.
     */
    MEDIUM(22),

    /**
     * Size 32x32.
     */
    LARGE(32),

    /**
     * Size 48x48.
     */
    HUGE(48);

    private final int sizePx;

    private IconSize(int sizePx) {
        this.sizePx = sizePx;
    }

    /**
     * Returns the size in pixels.
     *
     * @return the size in pixels.
     * @since 1.3
     */
    public int getSizePx() {
        return sizePx;
    }

    /**
     * Returns one smaller size.
     */
    public IconSize getOneSmaller() {
        switch (this) {
        case SMALL:
            return SMALL;
        case MEDIUM:
            return SMALL;
        case LARGE:
            return MEDIUM;
        case HUGE:
            return LARGE;
        default:
            return SMALL;
        }
    }

    /**
     * Returns two smaller size.
     */
    public IconSize getTwoSmaller() {
        switch (this) {
        case SMALL:
            return SMALL;
        case MEDIUM:
            return SMALL;
        case LARGE:
            return SMALL;
        case HUGE:
            return MEDIUM;
        default:
            return MEDIUM;
        }
    }
}
