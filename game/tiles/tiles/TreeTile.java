package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;

public class TreeTile extends Tile {
    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public TreeTile(int id) {
        super(id, null, "Tree", 2, 0, "Effect", 20, null);
    }

}
