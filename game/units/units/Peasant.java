package aog2.game.units.units;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.players.Player;
import aog2.game.units.Type;
import aog2.game.units.Unit;
import java.awt.Graphics;

/**
 *
 * @author adam 
 */
public class Peasant extends Unit {

    //Image, Player, Type, Name, 
    //minHP, maxHP, special, minrange, maxrange, 
    //effects, passive, speed
    public Peasant(Handler handler, Player player) {
        super(handler, Assets.peasant, player, Type.Helper, "Peasant",
                 5, "none", 1, 1,
                "none", "none", 6);
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        g.drawImage(getImage(), x, y, w, h, null);
    }
}
