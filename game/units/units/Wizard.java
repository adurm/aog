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
public class Wizard extends Unit {

    //Image, Player, Type, Name, 
    //minHP, maxHP, special, minrange, maxrange, 
    //effects, passive, speed
    public Wizard(Handler handler, Player player) {
        super(handler, Assets.wizard, player, Type.Wizard, "Wizard",
                20, "none", 3, 5,
                "none", "none", 3);
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        g.drawImage(getImage(), x, y, w, h, null);
    }
}
