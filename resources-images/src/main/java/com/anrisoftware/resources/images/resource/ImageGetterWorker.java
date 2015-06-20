package com.anrisoftware.resources.images.resource;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

final class ImageGetterWorker {

    public ImageIcon getImage(ImageResourceImpl resource,
            ImageResourceImplLogger log, Image image) {
        ImageIcon imageLoader = new ImageIcon(image);
        imageLoader = waitUntilImageIsLoaded(resource, log, image, imageLoader);
        return imageLoader;
    }

    private ImageIcon waitUntilImageIsLoaded(ImageResourceImpl resource,
            ImageResourceImplLogger log, Image image, ImageIcon imageLoader) {
        log.imageIsLoaded(resource, isImageLoaded(imageLoader));
        while (!isImageLoaded(imageLoader)) {
            Thread.yield();
        }
        return imageLoader;
    }

    private boolean isImageLoaded(ImageIcon imageLoader) {
        return imageLoader.getImageLoadStatus() != MediaTracker.LOADING
                || imageLoader.getImageLoadStatus() != MediaTracker.COMPLETE;
    }

}
