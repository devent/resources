package com.anrisoftware.resources.images.resource;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

final class ImageBufferedWorker {

    public BufferedImage toBuffered(Image image, int width, int height,
            int imageType) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        BufferedImage bimage = new BufferedImage(width, height, imageType);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

}
