package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.players.Player;
import aog2.game.tiles.Structure;

public class BluePortalTile extends Structure {

    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public BluePortalTile(int id) {
        super(id, null, "Portal", 1, 50, "Effect", 0, null);
    }
    
    @Override
    public void setOwner(Player p) {
        this.owner = p;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }
    
}
