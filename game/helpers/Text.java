
package aog2.game.helpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * 
 * @author adam
 * 
 * Class Text draws a String with at a location with given font and location
 * Also contains boolean center to center the String if needed
 */
public class Text {
    
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font f) {
        
        g.setColor(c);
        g.setFont(f);
        int x = xPos;
        int y = yPos;
        //if x,y points to where the center of the text should be, center it.
        if(center){
            FontMetrics fm = g.getFontMetrics(f);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight()/2) + fm.getAscent();
        }
        g.drawString(text,x, y);
    }
}
