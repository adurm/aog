package aog2.game.tiles.tiles;

import aog2.game.tiles.Tile;

public class OpenTile extends Tile {

    // ID, Player, Name, Speed, Mana, Effect, Defence, Unit
    public OpenTile(int id) {
        super(id, null, "Open", 1, 0, "Effect", 0, null);
    }

}
