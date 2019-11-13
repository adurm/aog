package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.players.Player;
import aog2.game.tiles.Structure;

public class TowerRoadTile extends Structure {
    
    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public TowerRoadTile(int id) {
        super(id, null, "Tower", 1, 75, "Effect", 30, null);
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
