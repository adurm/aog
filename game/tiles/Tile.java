package aog2.game.tiles;

import aog2.game.tiles.tiles.*;
import aog2.game.players.Player;
import aog2.game.units.Unit;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author adam class Tile represents the different tiles in the game and stores
 * them in a tiles[] array by their ID
 */
public class Tile implements Comparable<Tile> {

    //Tile array to hold the ID of our tiles
    public static Tile[] tiles = new Tile[256];

    //Standard width and height for tiles
    public static final int TILEWIDTH = 44, TILEHEIGHT = 44;

    //All tiles in the game
    public static Tile open = new OpenTile(10);
    public static Tile roadTile = new RoadTile(11);
    public static Tile rockTile = new RockTile(12);
    public static Tile mudTile = new MudTile(13);
    public static Tile riverTile = new RiverTile(14);
    public static Tile seaTile = new SeaTile(15);
    public static Tile treeTile = new TreeTile(16);
    public static Tile houseTile = new HouseTile(17);
    public static Tile houseRoadTile = new HouseRoadTile(18);
    public static Tile towerTile = new TowerTile(19);
    public static Tile towerRoadTile = new TowerRoadTile(20);
    public static Tile bluePortalTile = new BluePortalTile(21);
    public static Tile redPortalTile = new RedPortalTile(22);
    public static Tile emptyPortalTile = new EmptyPortalTile(23);
    public static Tile borderTile = new BorderTile(99);

    //Properties of tiles
    private BufferedImage texture;
    private int id;
    private Player owner;
    private final String name;
    private final double speed;
    private final int mana;
    private String effect;
    private final int defence;
    private Unit unit;

    private int x, y;

    public Tile(int id,
            Player owner, String name, double speed,
            int mana, String effect, int defence,
            Unit unit) {

        this.id = id;
        this.owner = owner;
        this.name = name;
        this.speed = speed;
        this.mana = mana;
        this.effect = effect;
        this.defence = defence;
        this.unit = unit;
        tiles[id] = this;

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage img) {
        texture = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player p) {
        owner = p;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMana() {
        return mana;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String e) {
        effect = e;
    }

    public int getDefence() {
        return defence;
    }

    public Unit getUnit() {
        return unit;
    }

    public void addUnit(Unit unit) {
        this.unit = unit;
    }

    public void removeUnit() {
        unit = null;
    }

    public boolean isStructure() {
        return id <= 23 && id >= 17;
    }

    public boolean isPortal() {
        return id == 21 || id == 22 || id == 23;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Tile t) {

        if (speed > t.getSpeed()) {
            return 1;
        } else if (speed < t.getSpeed()) {
            return -1;
        }
        return 0;
    }

}
