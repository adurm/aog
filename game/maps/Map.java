package aog2.game.maps;

import aog2.game.graphics.Assets;
import aog2.game.helpers.Handler;
import aog2.game.tiles.Tile;
import aog2.game.units.Unit;
import aog2.game.helpers.Utils;
import aog2.game.tiles.Bitmasking;
import java.awt.Graphics;

/**
 *
 * @author adam
 *
 * Class Map updates and draws the map to the screen
 */
public class Map {

    private Handler handler;
    private int width, height;
    private int[][] tileIDs; //the tile ID array which is what is read from the .txt file
    private Tile[][] tiles; //the array of tiles on the map
    private Unit[][] units; //the array of units on the map
    private int portals = 0, houses = 0, towers = 0;
    private Bitmasking bitmasking;

    public Map(Handler handler, String path) {

        this.handler = handler;

        loadWorld(path);
        tiles = new Tile[width][height];
        units = new Unit[width][height];

        bitmasking = new Bitmasking(handler, this);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                initTileArray(x, y);
                countStructures(x, y);
            }
        }
        initTileImages();
    }

    private void initTileArray(int x, int y) {

        tiles[x][y] = new Tile(getTileIDat(x, y).getId(),
                getTileIDat(x, y).getOwner(), getTileIDat(x, y).getName(),
                getTileIDat(x, y).getSpeed(), getTileIDat(x, y).getMana(),
                getTileIDat(x, y).getEffect(), getTileIDat(x, y).getDefence(),
                getTileIDat(x, y).getUnit());
        tiles[x][y].setX(x);
        tiles[x][y].setY(y);

    }

    private void initTileImages() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y].setTexture(bitmasking.getImage(tiles[x][y]));
            }
        }

    }

    private void countStructures(int x, int y) {

        if (getTileAt(x, y).getId() == 17
                || getTileAt(x, y).getId() == 18) {
            houses++;
        }
        if (getTileAt(x, y).getId() == 19
                || getTileAt(x, y).getId() == 20) {
            towers++;
        }
        if (getTileAt(x, y).getId() == 21
                || getTileAt(x, y).getId() == 22
                || getTileAt(x, y).getId() == 23) {
            portals++;
        }
    }

    public void tick() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                units[x][y] = tiles[x][y].getUnit();
                if (units[x][y] != null) {
                    if (units[x][y].getOwner() == handler.getPlayer(0)) {
                        handler.getPlayer(0).addUnit(units[x][y]);
                    } else if (units[x][y].getOwner() == handler.getPlayer(1)) {
                        handler.getPlayer(1).addUnit(units[x][y]);
                    }
                }
            }
        }
    }

    //Renders only the area of the map visible by the camera
    public void render(Graphics g) {

        for (int y = handler.getPosition().getScreenYStart(); y < handler.getPosition().getScreenYEnd(); y++) {
            for (int x = handler.getPosition().getScreenXStart(); x < handler.getPosition().getScreenXEnd(); x++) {
                tiles[x][y].render(g,
                        handler.getPosition().fixToLocWithXCoord(x),
                        handler.getPosition().fixToLocWithYCoord(y));
            }
        }
    }

    //reads the path (map.txt files) and converts it to Tiles
    private void loadWorld(String path) {

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tileIDs = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tileIDs[x][y] = Utils.parseInt(tokens[(x + (y * width)) + 2]);
            }
        }
    }

    //find tile in tiles[][] at tiles[x][y]
    public Tile getTileIDat(int x, int y) {

        if (x < 0 || y < 0 || x > width || y > height) {
            return Tile.borderTile;
        }

        Tile t = Tile.tiles[tileIDs[x][y]];
        if (t == null) {
            return Tile.borderTile;
        }
        return t;
    }
    
    public void changeTile(int x, int y, Tile newTile, Unit newUnit) {
        
        
        tiles[x][y] = newTile;
        if (newUnit.getOwner() == handler.getPlayer(0)){
            tiles[x][y].setTexture(Assets.blueportalroadtile);
        } else {
            tiles[x][y].setTexture(Assets.redportalroadtile);
        }
        newTile.addUnit(newUnit);
        units[x][y] = newUnit;
    }

    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }

    public int[][] getTileIDs() {
        return tileIDs;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addUnit(Unit u, int x, int y) {
        units[x][y] = u;
    }

    public Unit getUnit(int x, int y) {
        return units[x][y];
    }

    public void removeUnit(int x, int y) {
        units[x][y] = null;
    }

    public Unit[][] getUnits() {
        return units;
    }

    public int getPortals() {
        return portals;
    }

    public int getHouses() {
        return houses;
    }

    public int getTowers() {
        return towers;
    }
    
}
