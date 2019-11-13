package aog2.game.helpers;

import aog2.game.tiles.Tile;

/**
 * 
 * @author adam
 * class Position
 * helper class to get positions on the map
 */
public class Position {

    private Handler handler;

    //class to get positions and fix locations
    public Position(Handler handler) {
        this.handler = handler;
    }

    ///////////////Fixes to a location on the map with coords given
    public int fixToLocWithXCoord(int xCoord) {
        return (xCoord * Tile.TILEWIDTH) - handler.getGameCamera().getxOffset();
    }

    public int fixToLocWithYCoord(int yCoord) {
        return (yCoord * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset();
    }

    ///////////////Fixes to the tile your mouse is on
    public int fixToMouseXTile() {
        return Utils.round((handler.getMouseManager().getMouseX()
                + (handler.getGameCamera().getxOffset() % Tile.TILEWIDTH)), Tile.TILEWIDTH)
                - (handler.getGameCamera().getxOffset() % Tile.TILEWIDTH);
    }

    public int fixToMouseYTile() {
        return Utils.round((handler.getMouseManager().getMouseY()
                + (handler.getGameCamera().getyOffset() % Tile.TILEHEIGHT)), Tile.TILEHEIGHT)
                - (handler.getGameCamera().getyOffset() % Tile.TILEHEIGHT);
    }

    /////////////////////returns the coordinate of the tile the mouse is on
    public int getCoordX() {
        return Utils.round(handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset(),
                Tile.TILEWIDTH) / Tile.TILEWIDTH;
    }

    public int getCoordY() {
        return Utils.round(handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH) / Tile.TILEWIDTH;
    }

    /////////////////////////returns the fixed location of the tile on the map
    public int getCoordXLoc() {
        return getCoordX() * Tile.TILEWIDTH;
    }

    public int getCoordYLoc() {
        return getCoordY() * Tile.TILEHEIGHT;
    }

    //////////////////////////returns the start and end of the map visible on the screen
    public int getScreenXStart() {
        return Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
    }

    public int getScreenYStart() {
        return Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
    }

    public int getScreenXEnd() {
        return Math.min(handler.getMap().getWidth(),
                (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
    }

    public int getScreenYEnd() {
        return Math.min(handler.getMap().getHeight(),
                (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
    }

}
