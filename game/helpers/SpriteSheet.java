package aog2.game.helpers;

import java.awt.image.BufferedImage;

/**
 * 
 * @author adam
 * 
 * Class SpriteSheet containing the spritesheets used
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    //Cropping method to find a certain section of the spritesheet
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }

}
