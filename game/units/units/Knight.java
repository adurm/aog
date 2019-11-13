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
public class Knight extends Unit {

    //Image, Player, Type, Name, 
    //minHP, maxHP, special, minrange, maxrange, 
    //effects, passive, speed
    public Knight(Handler handler, Player player) {
        super(handler, Assets.knight, player, Type.Warrior, "Knight",
                 25, "none", 1, 1,
                "none", "none", 5);
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        g.drawImage(getImage(), x, y, w-5, h, null);
    }
}
