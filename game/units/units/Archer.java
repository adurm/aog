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
public class Archer extends Unit {

    //Image, Player, Type, Name, 
    //minHP, maxHP, special, minrange, maxrange, 
    //effects, passive, speed
    public Archer(Handler handler, Player player) {
        super(handler, Assets.archer, player, Type.Archer, "Archer",
                20, "none", 2, 3,
                "none", "none", 6);
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        g.drawImage(getImage(), x, y, w, h, null);
    }
}
