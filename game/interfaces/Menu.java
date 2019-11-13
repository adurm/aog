package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author adam
 *
 * Menu appears when clicking escape key
 */
public class Menu extends Interface {

    private final int w = 300;
    private final int h = 400;
    private final int x = (handler.getWidth() - w) / 2;
    private final int y = (handler.getHeight() - h) / 2;
    private int onlyonce;

    public Menu(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            menuOpen = !menuOpen;
        }
    }

    @Override
    public void render(Graphics g) {

        if (menuOpen) {
            if (onlyonce++ == 0) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
                return;
            }
            g.setColor(Color.black);
            g.fillRect(x, y, w, h);
            Text.drawString(g, "Menu",
                    (handler.getWidth() / 2), (handler.getHeight() / 2),
                    true, Color.white, Assets.font20);
        } else {
            onlyonce = 0;
        }
    }
}
