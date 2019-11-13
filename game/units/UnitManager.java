package aog2.game.units;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author adam class UnitManager ticks and renders the units
 */
public class UnitManager {

    private Handler handler;
    private Unit[][] units;

    public UnitManager(Handler handler) {
        this.handler = handler;
        units = handler.getMap().getUnits();
    }

    public void render(Graphics g) {

        for (int i = 0; i < handler.getMap().getUnits().length; i++) {
            for (int j = 0; j < handler.getMap().getUnits()[0].length; j++) {
                if (units[i][j] != null) {
                    drawUnitOwner(g, i, j);
                    drawUnit(g, i, j);
                    drawUnitMoveStatus(g, i, j);
                }
            }
        }
    }

    private void drawUnitOwner(Graphics g, int i, int j) {

        g.setColor(units[i][j].getOwner().getColor());
        for (int a = -2; a < 5; a++) {
            g.drawOval(handler.getPosition().fixToLocWithXCoord(i),
                    handler.getPosition().fixToLocWithYCoord(j) + a + (Tile.TILEHEIGHT / 3 * 2),
                    Tile.TILEWIDTH, Tile.TILEHEIGHT / 3);
        }
    }

    private void drawUnit(Graphics g, int i, int j) {

        units[i][j].render(g, handler.getPosition().fixToLocWithXCoord(i),
                handler.getPosition().fixToLocWithYCoord(j+1)- Unit.UNITHEIGHT,
                Unit.UNITWIDTH, Unit.UNITHEIGHT);
    }

    private void drawUnitMoveStatus(Graphics g, int i, int j) {
        
        if (units[i][j].hasMoved()) {
            g.drawImage(Assets.yellowmove,
                    handler.getPosition().fixToLocWithXCoord(i),
                    handler.getPosition().fixToLocWithYCoord(j + 1) - 20,
                    20, 20, null);
        }
        if (units[i][j].hasAttacked()) {
            g.drawImage(Assets.redmove,
                    handler.getPosition().fixToLocWithXCoord(i),
                    handler.getPosition().fixToLocWithYCoord(j + 1) - 20,
                    20, 20, null);
        }
    }

}
