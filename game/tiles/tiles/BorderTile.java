package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;

public class BorderTile extends Tile {
    
    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public BorderTile(int id) {
        super(id, null, "Border", 99, 0, "Effect", 0, null);
    }

}
