package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;

public class RockTile extends Tile {

    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public RockTile(int id) {
        super(id, null, "Rock", 10, 0, "Effect", 40, null);
    }

}
