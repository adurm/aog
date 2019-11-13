package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.units.Unit;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author adam Class Minimap generates the minimap based on the map
 */
public class Minimap extends Interface {

    private final int xLoc, yLoc;
    private final int width, height;
    private final int tileWidth, tileHeight;

    public Minimap(Handler handler) {

        super(handler);
        xLoc = 10;
        yLoc = 120;
        width = 220;
        height = 220;
        tileWidth = width / handler.getMap().getWidth();
        tileHeight = height / handler.getMap().getHeight();
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

        if (mapOpen) {
            for (int i = 0; i < handler.getMap().getWidth(); i++) {
                for (int j = 0; j < handler.getMap().getHeight(); j++) {
                    //Check Tile
                    checkTile(g, i, j); //sets the color
                    g.fillRect(xLoc + (tileWidth * i), yLoc + (tileHeight * j), tileWidth, tileHeight);
                    g.setColor(Color.darkGray);
                    g.drawLine(xLoc + tileWidth, yLoc + (tileHeight * j), xLoc + width - tileWidth, yLoc + (tileHeight * j));
                    //Check Unit
                    if (handler.getMap().getUnit(i, j) != null) {
                        checkUnit(g, i, j); //sets the color
                        g.fillOval(xLoc + (tileWidth * i) + 1, yLoc + (tileHeight * j) + 1,
                                tileWidth-2, tileWidth-2);
                    }

                }
                g.setColor(Color.darkGray);
                g.drawLine(xLoc + (tileWidth * i), yLoc + tileHeight, xLoc + (tileWidth * i), yLoc + height - tileHeight);
            }
            g.setColor(Color.black);
            g.drawRect(xLoc, yLoc, width, height);
        }
    }

    //sets the color according to the tile of the map
    public void checkTile(Graphics g, int x, int y) {

        int z = handler.getMap().getTileIDs()[x][y];
        switch (z) {
            case 10: //open tile
                g.setColor(new Color(50,205,50));
                break;
            case 11: //road tile
                g.setColor(Color.lightGray);
                break;
            case 12: //rock tile
                g.setColor(Color.darkGray);
                break;
            case 13: //mud tile
                g.setColor(new Color(139, 69, 19));
                break;
            case 14: //river tile
                g.setColor(new Color(123, 104, 238));
                break;
            case 15: //sea tile
                g.setColor(new Color(30, 144, 255));
                break;
            case 16: //tree tile
                g.setColor(new Color(34,139,34));
                break;
            case 17: //structures
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                if (handler.getPlayer(0).equals(handler.getMap().getTileAt(x, y).getOwner())) {
                    g.setColor(new Color(30,144,255));
                } else if (handler.getPlayer(1).equals(handler.getMap().getTileAt(x, y).getOwner())) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.gray);
                }
                break;
            default: //border tile
                g.setColor(Color.orange);
                break;
        }
    }

    public void checkUnit(Graphics g, int x, int y) {

        if (handler.getMap().getTileAt(x, y).getUnit() != null) {

            Unit u = handler.getMap().getTileAt(x, y).getUnit();
            if (u.getOwner() == handler.getPlayer(0)) {
                g.setColor(new Color(30,144,255));
            } else if (u.getOwner() == handler.getPlayer(1)) {
                g.setColor(Color.red);
            }
        }
    }

}
