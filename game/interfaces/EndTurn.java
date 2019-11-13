package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import aog2.game.turns.TurnManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam
 *
 * End turn button triggers the turnManager switch turn method when clicked
 */
public class EndTurn extends Interface {

    private final int x, y, width, height;
    private boolean hover = false;
    

    public EndTurn(Handler handler) {
        super(handler);
        x = 15;
        y = 92;
        width = 90;
        height = 20;
        turnManager = new TurnManager(handler);
    }

    @Override
    public void tick() {

        int mousex = handler.getMouseManager().getMouseX();
        int mousey = handler.getMouseManager().getMouseY();

        hover = mousex <= x + width
                && mousex >= x
                && mousey <= y + height
                && mousey >= y;

        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)
                && hover) {
            turnManager.switchTurn();
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.endturn, x, y, null);
        if (hover) {
            g.drawImage(Assets.endturnhover, x, y, null);
        }
        Text.drawString(g, "END TURN", 60, 102, true, Color.red, Assets.font9);
        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)
                && hover) {
            turnManager.showTurnStart(g);
        }
    }

}
