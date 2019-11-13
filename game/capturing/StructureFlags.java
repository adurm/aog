package aog2.game.capturing;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author adam
 */
public class StructureFlags {

    private Handler handler;

    public StructureFlags(Handler handler) {
        this.handler = handler;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        for (int i = 0; i < handler.getPlayers().length; i++) {
            
            BufferedImage flag;
            if (handler.getPlayers()[i].equals(handler.getPlayer(0))) {
                flag = Assets.blueflag;
            } else {
                flag = Assets.redflag;
            }
            for (Tile t : handler.getPlayers()[i].getTowers()) {
                g.drawImage(flag, handler.getPosition().fixToLocWithXCoord(t.getX()),
                        handler.getPosition().fixToLocWithYCoord(t.getY()), null);
            }
            for (Tile t : handler.getPlayers()[i].getHouses()) {
                g.drawImage(flag, handler.getPosition().fixToLocWithXCoord(t.getX()),
                        handler.getPosition().fixToLocWithYCoord(t.getY()), null);

            }
        }
    }

}
