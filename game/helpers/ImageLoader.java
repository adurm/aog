package aog2.game.helpers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * @author adam
 * 
 * Class ImageLoader converts file paths into an image
 */
public class ImageLoader {

    public static BufferedImage loadImage(String path) {

        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ex) {
        }
        return null;
    }
}
