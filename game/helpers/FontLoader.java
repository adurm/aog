
package aog2.game.helpers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author adam
 * 
 * class FontLoader converts file paths into a font asset
 */
public class FontLoader {
    
    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            System.exit(0);
        }
        return null;
    }

}
