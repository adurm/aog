package aog2.game.portal;

import aog2.game.helpers.Handler;
import aog2.game.players.Player;
import aog2.game.tiles.Tile;
import java.awt.Graphics;


/**
 *
 * @author adam class Portal handles the portal interface and summoning units
 * portal package will be split into: - abstract portal - neutral and 3 factions
 */
public abstract class Portal {

    protected Handler handler;
    final int width = Tile.TILEWIDTH;
    final int height = Tile.TILEHEIGHT;
    static boolean portalOpen;
    static int portalx, portaly;
    static Player owner;

    public Portal(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}                  