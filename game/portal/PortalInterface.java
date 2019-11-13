package aog2.game.portal;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class PortalInterface extends Portal {
    
    

    public PortalInterface(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {

            //keep track of the tile you're clicking on
            int xCoord = handler.getPosition().getCoordX();
            int yCoord = handler.getPosition().getCoordY();

            //if clicking outside of map do nothing
            if (xCoord > handler.getMap().getWidth() - 2
                    || xCoord < 1
                    || yCoord < 1
                    || yCoord > handler.getMap().getHeight() - 2) {
                return;
            }

            //if clicking on ur portal on ur turn
            Tile t = handler.getMap().getTileAt(xCoord, yCoord);             
            if (t.isPortal()
                    && t.getUnit() == null
                    && t.getOwner() == handler.getPlayersTurn()) {
                owner = t.getOwner();
                portalOpen = !portalOpen;
            }
            if (handler.getPlayersTurn() == handler.getPlayer(0)){
                portalx = 4;
                portaly = 4;
            } else {
                portalx = 17;
                portaly = 17;
            }
        }
    }

    @Override
    public void render(Graphics g) {

        int x1;
        int y1;
        if (owner == handler.getPlayer(0)){
            x1 = handler.getPosition().fixToLocWithXCoord(4);
            y1 = handler.getPosition().fixToLocWithYCoord(4);
        } else {
            x1 = handler.getPosition().fixToLocWithXCoord(17);
            y1 = handler.getPosition().fixToLocWithYCoord(17);
        }
        
        if (portalOpen) {
            g.drawImage(Assets.white, x1 - width, y1, width, height, null);
            g.drawImage(Assets.knight, x1 - width, y1, width, height, null);
            
            g.drawImage(Assets.white, x1, y1 + height, width, height, null);
            g.drawImage(Assets.wizard, x1, y1 + height, width, height, null);
            
            g.drawImage(Assets.white, x1, y1 - height, width, height, null);
            g.drawImage(Assets.peasant, x1, y1 - height, width, height, null);
             
            g.drawImage(Assets.white, x1 + width, y1, width, height, null);
            g.drawImage(Assets.archer, x1 + width, y1, width, height, null);
        }
    }

}
