package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import aog2.game.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UnitHoverPanel extends Interface {

    private boolean hover;
    private Tile tile;
    private int x = 60, y = 50;
    private int xCoord, yCoord;

    public UnitHoverPanel(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        xCoord = handler.getPosition().getCoordX();
        yCoord = handler.getPosition().getCoordY();

        //if clicking outside of map do nothing
        if (xCoord > handler.getMap().getWidth() - 2
                || xCoord < 1
                || yCoord < 1
                || yCoord > handler.getMap().getHeight() - 2) {
            return;
        }

        tile = handler.getMap().getTileAt(handler.getPosition().getCoordX(),
                handler.getPosition().getCoordY());
        hover = tile.getUnit() != null;
    }

    @Override
    public void render(Graphics g) {

        //if clicking outside of map do nothing
        if (xCoord > handler.getMap().getWidth() - 2
                || xCoord < 1
                || yCoord < 1
                || yCoord > handler.getMap().getHeight() - 2) {
            return;
        }

        if (hover) {

            int xLoc = handler.getPosition().getCoordXLoc() - handler.getGameCamera().getxOffset() - x;
            int yLoc = handler.getPosition().getCoordYLoc() - handler.getGameCamera().getyOffset() - y;

            BufferedImage moveStatus = Assets.greenmove;
            if (tile.getUnit().hasAttacked()) {
                moveStatus = Assets.redmove;
            } else if (tile.getUnit().hasMoved()) {
                moveStatus = Assets.yellowmove;
            }

            g.drawImage(Assets.unithoverpanel, xLoc - 20, yLoc - 20, 150,80, null);
            Text.drawString(g, tile.getUnit().getName(), xLoc - 10, yLoc + 1, false, Color.white, Assets.font16);
            Text.drawString(g, tile.getUnit().getCurrentHP() + "/" + tile.getUnit().getMaxHP(), xLoc + 80, yLoc, false, Color.white, Assets.font16);
            Text.drawString(g, "" + tile.getDefence() + "%", xLoc - 11, yLoc + 28, false, Color.white, Assets.font16);
            g.drawImage(moveStatus, xLoc + 40, yLoc + 12, 22, 22, null);
            g.drawImage(tile.getUnit().getTypeImg(), xLoc + 93, yLoc + 15, 22,22,null);
        }
    }

}
