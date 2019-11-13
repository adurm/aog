package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author adam
 * ManaBar displays the mana on an interface
 */
public class ManaBar extends Interface {

    private final int x, y, width, height;
    private boolean hover = false;
    private int mousex, mousey;

    public ManaBar(Handler handler) {
        super(handler);
        x = 150;
        y = 10;
        width = 70;
        height = 35;
    }

    @Override
    public void tick() {

        mousex = handler.getMouseManager().getMouseX();
        mousey = handler.getMouseManager().getMouseY();

        hover = mousex <= x + width
                && mousex >= x
                && mousey <= y + height
                && mousey >= y;
    }

    @Override
    public void render(Graphics g) {

        int mana = handler.getPlayersTurn().getMana();
        g.drawImage(Assets.manabar2, x, y, null);
        Text.drawString(g, ""+mana, x - 10 + width / 2, y + height / 2, true, Color.white, Assets.font16);
        if (hover) {
            g.setColor(Color.black);
            g.fillRect(mousex - 6, mousey + 10, 100, 20);
            Text.drawString(g, "You have "+mana+" Mana.", mousex + 45, mousey + 20, 
                    true, Color.white, Assets.font9);
        }
    }

}
