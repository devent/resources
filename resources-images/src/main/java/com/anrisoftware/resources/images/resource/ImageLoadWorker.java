package com.anrisoftware.resources.images.resource;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.anrisoftware.resources.api.ResourcesException;

final class ImageLoadWorker {

    public Image loadImage(ImageResourceImpl resource,
            ImageResourceImplLogger log, URL url) throws ResourcesException {
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw log.errorLoadingImage(resource, e);
        }
    }
}
