package aog2.game.tiles.tiles;

import aog2.game.graphics.Assets;
import aog2.game.tiles.Tile;
import java.math.BigDecimal;

public class RoadTile extends Tile {
    // Image, ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public RoadTile(int id) {
        super(id, null, "Road", (double) 2/3, 0, "Effect", 0, null);
    }

}
