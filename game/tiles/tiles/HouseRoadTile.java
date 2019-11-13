package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.players.Player;
import aog2.game.tiles.Structure;

public class HouseRoadTile extends Structure {

    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public HouseRoadTile(int id) {
        super(id, null, "House", 1, 25, "Effect", 20, null);
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
