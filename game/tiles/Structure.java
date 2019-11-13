
package aog2.game.tiles;

import aog2.game.players.Player;
import aog2.game.units.Unit;
import java.awt.image.BufferedImage;

/**
 * 
 * @author adam
 * class Structure is a Tile that can be captured
 */
public abstract class Structure extends Tile {
    
    protected Player owner;
    
    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public Structure(int id, Player owner, 
            String name, int speed, int mana, String effect, 
            int defence, Unit unit) {
        super(id, owner, name, speed, mana, effect, defence, unit);
    }
    
    public abstract void setOwner(Player p);
    
    @Override
    public abstract Player getOwner();
    
    
    
}
