package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import aog2.game.turns.TurnManager;
import aog2.game.players.Player;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author adam Class Progress panel displayed on the bottom left
 */
public class ProgressPanel extends Interface {

    private final int width = 100, height = 120;
    private final int x = 0, y = handler.getHeight() - height - 20;
    private boolean hover = false;
    private int mousex, mousey;

    public ProgressPanel(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        mousex = handler.getMouseManager().getMouseX();
        mousey = handler.getMouseManager().getMouseY();

        hover = mousex <= x + width
                && mousey <= y + height
                && mousey >= y;
    }

    @Override
    public void render(Graphics g) {

        Player p = handler.getPlayersTurn();
        Color c = handler.getPlayersTurn().getColor();

        g.drawImage(Assets.progresspanel2, x, y, null);
        Text.drawString(g, TurnManager.turnsplayed + "/20", x + 65, y + 15, true, Color.white, Assets.font16);
        Text.drawString(g, p.getPortals().size() + "/" + handler.getMap().getPortals(), x + 65, y + 45, true, c, Assets.font16);
        Text.drawString(g, p.getTowers().size() + "/" + handler.getMap().getTowers(), x + 65, y + 75, true, c, Assets.font16);
        Text.drawString(g, p.getHouses().size() + "/" + handler.getMap().getHouses(), x + 65, y + 102, true, c, Assets.font16);

        if (hover) {

            g.setColor(Color.black);
            if (mousex > x + 37
                    && mousey > y + 4
                    && mousex < x + 94
                    && mousey < y + 30) {
                g.fillRect(mousex, mousey + 10, 80, 20);
                Text.drawString(g, "Turn " + TurnManager.turnsplayed + " of 20",
                        mousex + 40, mousey + 20, true, Color.white, Assets.font9);
            } else if (mousex > x + 37
                    && mousey > y + 32
                    && mousex < x + 94
                    && mousey < y + 58) {
                g.fillRect(mousex, mousey + 10, 80, 20);
                String s;
                if (p.getPortals().size() == 1) {
                    s = "You own " + p.getPortals().size() + " portal";
                } else {
                    s = "You own " + p.getPortals().size() + " portals";
                }
                Text.drawString(g, s, mousex + 40, mousey + 20, true, Color.white, Assets.font9);
            } else if (mousex > x + 37
                    && mousey > y + 61
                    && mousex < x + 94
                    && mousey < y + 86) {
                g.fillRect(mousex, mousey + 10, 80, 20);
                String s;
                if (p.getTowers().size() == 1) {
                    s = "You own " + p.getTowers().size() + " tower";
                } else {
                    s = "You own " + p.getTowers().size() + " towers";
                }
                Text.drawString(g, s, mousex + 40, mousey + 20, true, Color.white, Assets.font9);
            } else if (mousex > x + 37
                    && mousey > y + 89
                    && mousex < x + 94
                    && mousey < y + 115) {
                g.fillRect(mousex, mousey + 10, 80, 20);
                String s;
                if (p.getHouses().size() == 1) {
                    s = "You own " + p.getHouses().size() + " house";
                } else {
                    s = "You own " + p.getHouses().size() + " houses";
                }
                Text.drawString(g, s, mousex + 40, mousey + 20, true, Color.white, Assets.font9);
            }
        }
    }

}
