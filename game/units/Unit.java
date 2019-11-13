package aog2.game.units;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.players.Player;
import aog2.game.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author adam abstract class Unit
 */
public abstract class Unit {

    //Statics
    static final int UNITWIDTH = Tile.TILEWIDTH;
    static final int UNITHEIGHT = Tile.TILEHEIGHT + Tile.TILEHEIGHT/2;

    protected Handler handler;

    //Unit properties
    private final BufferedImage image;
    private BufferedImage typeImg;
    private final Player owner;
    private final Type type;
    private final String name;
    private int maxHP;
    private final String special;
    private final int minRange;
    private final int maxRange;
    private final String effects;
    private final String passive;
    private final int speed;
    
    //While in play
    private boolean hasMoved = false;
    private boolean hasAttacked = false;
    private int currentHP;

    public Unit(Handler handler, BufferedImage image, Player owner,
            Type type, String name, int maxHP,
            String special, int minRange, int maxRange,
            String effects, String passive, int speed) {

        this.handler = handler;
        this.image = image;
        this.owner = owner;
        this.type = type;
        this.name = name;
        this.currentHP = maxHP;
        this.maxHP = maxHP;
        this.special = special;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.effects = effects;
        this.passive = passive;
        this.speed = speed;

        setTypeImg();
    }
    
    public abstract void render(Graphics g, int x, int y, int w, int h);

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public String getSpecial() {
        return special;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public String getEffects() {
        return effects;
    }

    public String getPassive() {
        return passive;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isActive() {
        return !hasAttacked() && getOwner() == handler.getPlayersTurn();
    }

    private void setTypeImg() {

        if (getType() != null) {
            switch (getType()) {
                case Warrior:
                    typeImg = Assets.warriorIcon;
                    break;
                case Wizard:
                    typeImg = Assets.wizardIcon;
                    break;
                case Archer:
                    typeImg = Assets.archerIcon;
                    break;
                case Helper:
                    typeImg = Assets.helperIcon;
                    break;
                default:
                    break;
            }
        }
    }

    public BufferedImage getTypeImg() {
        return typeImg;
    }

}
