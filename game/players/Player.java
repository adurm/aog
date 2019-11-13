package aog2.game.players;

import aog2.game.helpers.Handler;
import aog2.game.tiles.Tile;
import aog2.game.units.Unit;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author adam class Player is the real player
 *
 */
public class Player {

    private Handler handler;
    private Color color;
    private int mana;
    private ArrayList<Tile> portals, houses, towers;
    private ArrayList<Unit> units;

    public Player(Handler handler, Color color) {

        this.handler = handler;
        portals = new ArrayList<>();
        houses = new ArrayList<>();
        towers = new ArrayList<>();
        units = new ArrayList<>();
        this.color = color;

        initManaAndScore();
    }

    private void initManaAndScore() {
        
        //player one
        if (new Color(30, 144, 255).equals(color)) {
            mana = -30;
        } //player two
        else {
            mana = 20;
        }
    }

    public int getMana() {
        return mana;
    }

    public boolean removeMana(int mana) {
        if (this.mana >= mana){
            this.mana -= mana;
            return true;
        } return false;
    }

    public void addMana(int mana) {
        this.mana += mana;
    }

    public ArrayList<Tile> getPortals() {
        return portals;
    }

    public void addPortal(Tile t) {
        portals.add(t);
    }

    public void removePortal(Tile t) {
        portals.remove(t);
    }

    public ArrayList<Tile> getHouses() {
        return houses;
    }

    public void addHouse(Tile t) {
        houses.add(t);
    }

    public void removeHouse(Tile t) {
        houses.remove(t);
    }

    public ArrayList<Tile> getTowers() {
        return towers;
    }

    public void addTower(Tile t) {
        towers.add(t);
    }

    public void removeTower(Tile t) {
        towers.remove(t);
    }

    public Color getColor() {
        return color;
    }

    public void addStructure(Tile t) {

        if (null != t.getName()
                && t.isStructure()) {
            switch (t.getName()) {
                case "Portal":
                    addPortal(t);
                    break;
                case "House":
                    addHouse(t);
                    break;
                case "Tower":
                    addTower(t);
                    break;
                default:
                    break;
            }
        }

    }
    
    public ArrayList<Unit> getUnits(){
        return units;
    }
    
    public void addUnit(Unit u){
        units.add(u);
    }
    
    public void removeUnit(Unit u){
        units.remove(u);
    }
    
    public boolean hasPeasentsOrChief(){
        
        for (int i = 0; i < handler.getMap().getWidth(); i ++){
            for (int j = 0; j < handler.getMap().getHeight(); j ++){
                if (handler.getMap().getUnit(i, j) != null){
                    if (handler.getMap().getUnit(i, j).getOwner() == this
                            && ("Peasant".equals(handler.getMap().getUnit(i, j).getName())
                            ||  "Chief".equals(handler.getMap().getUnit(i, j).getName()))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
}
