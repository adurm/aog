package aog2.game.graphics;

import aog2.game.helpers.Handler;
import aog2.game.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam
 *
 * Class TileHover highlights the tile the mouse is on
 */
public class TileHover {

    private Handler handler;
    private boolean highlight;
    private int xTile, yTile;
    private int prevxTile, prevyTile;

    public TileHover(Handler handler) {
        this.handler = handler;
    }

    public void tick() {

        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {
            
            if (handler.getPosition().getCoordX() < 1
                    || handler.getPosition().getCoordX() > handler.getMap().getWidth() - 2
                    || handler.getPosition().getCoordY() > handler.getMap().getHeight() - 2
                    || handler.getPosition().getCoordY() < 1){
                return;
            }

            xTile = handler.getPosition().getCoordX();
            yTile = handler.getPosition().getCoordY();
            
            

            highlight = !(prevxTile == xTile
                    && prevyTile == yTile);

            prevxTile = xTile;
            prevyTile = yTile;
        }

    }

    public void render(Graphics g) {

        int x = handler.getPosition().fixToMouseXTile();
        int y = handler.getPosition().fixToMouseYTile();

        //highlight the tile selected
        if (highlight) {
            g.setColor(new Color(255, 255, 255, 60));
            g.fillRect(handler.getPosition().fixToLocWithXCoord(xTile),
                    handler.getPosition().fixToLocWithYCoord(yTile), Tile.TILEWIDTH, Tile.TILEHEIGHT);
        }

        //if the tile is in bounds of the map
        if (handler.getPosition().getCoordX() > handler.getMap().getWidth() - 2
                || handler.getPosition().getCoordX() < 1
                || handler.getPosition().getCoordY() < 1
                || handler.getPosition().getCoordY() > handler.getMap().getHeight() - 2) {
            return;
        }

        //indicate the corners of the tile
        g.setColor(Color.white);
        g.drawLine(x, y - 2, x, y + 2);
        g.drawLine(x - 2, y, x + 2, y);
        g.drawLine(x + Tile.TILEWIDTH, y - 2, x + Tile.TILEWIDTH, y + 2);
        g.drawLine(x + Tile.TILEWIDTH - 2, y, x + Tile.TILEWIDTH + 2, y);
        g.drawLine(x, y + Tile.TILEHEIGHT - 2, x, y + Tile.TILEHEIGHT + 2);
        g.drawLine(x - 2, y + Tile.TILEHEIGHT, x + 2, y + Tile.TILEHEIGHT);
        g.drawLine(x + Tile.TILEWIDTH, y + Tile.TILEHEIGHT - 2, x + Tile.TILEWIDTH, y + Tile.TILEHEIGHT + 2);
        g.drawLine(x + Tile.TILEWIDTH - 2, y + Tile.TILEHEIGHT, x + Tile.TILEWIDTH + 2, y + Tile.TILEHEIGHT);
    }
}
