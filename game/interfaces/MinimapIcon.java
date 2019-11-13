package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * 
 * @author adam
 * Class Minimapicon controls the opening and closing of Minimap
 */
public class MinimapIcon extends Interface {

    private final int xLoc, yLoc, width, height;
    private boolean hover = false;

    public MinimapIcon(Handler handler) {
        super(handler);
        xLoc = 105;
        yLoc = 10;
        width = 40;
        height = 40;
    }

    public void tick() {

        //Keep track of location of mouse
        int xMouseLoc = handler.getMouseManager().getMouseX();
        int yMouseLoc = handler.getMouseManager().getMouseY();

        hover = xMouseLoc <= xLoc + width
                && xMouseLoc >= xLoc
                && yMouseLoc <= yLoc + height
                && yMouseLoc >= yLoc;
        
        if (hover && handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1))
            mapOpen = !mapOpen;
    }

    public void render(Graphics g) {

        if (!hover) 
            g.drawImage(Assets.globe2, xLoc, yLoc, width, height, null);
        else g.drawImage(Assets.globehover2, xLoc, yLoc, 38, 37, null);
    }

}
