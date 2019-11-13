package aog2.game.turns;

import aog2.game.capturing.StructureCapturing;
import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import aog2.game.interfaces.Clock;
import aog2.game.players.Player;
import aog2.game.units.Unit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 *
 * @author adam class TurnManager responsible for updating things at the
 * start/end of every turn
 */
public class TurnManager {

    public static boolean[] turn;
    public static int turnsplayed = 0;
    public static boolean switchingTurn;

    private Handler handler;
    private Clock clock;
    private int startTurnMana;

    public TurnManager(Handler handler) {
        
        this.handler = handler;
        turn = new boolean[handler.getPlayers().length];
        turn[1] = true;
        turn[0] = false;
        clock = new Clock(handler);
    }

    public void tick() {
        clock.tick();
    }

    public void render(Graphics g) {
        clock.render(g);
    }

    public void showTurnStart(Graphics g) {

        drawStartOfTurnScreen(g);
        startTurnAfterClick();
    }

    public void switchTurn() {

        activateUnits();
        rotateTurns();
        startOfTurnMana();
        incrementTurnsPlayed();
        StructureCapturing.incrementCapturePercentage(handler.getPlayersTurn());
        moveCamera();
    }

    private void activateUnits() {

        for (int i = 0; i < handler.getMap().getUnits().length; i++) {
            for (int j = 0; j < handler.getMap().getUnits()[0].length; j++) {
                if (handler.getMap().getUnit(i, j) != null) {
                    Unit u = handler.getMap().getUnit(i, j);
                    if (u.getOwner() == handler.getPlayersTurn()) {
                        u.setHasMoved(false);
                        u.setHasAttacked(false);
                    }
                }
            }
        }
    }

    private void drawStartOfTurnScreen(Graphics g) {

        int width = 200;
        int height = 100;

        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

        g.setColor(handler.getPlayersTurn().getColor());
        g.fillRect(handler.getWidth() / 2 - width / 2, handler.getHeight() / 2 - height / 2, width, height);
        Text.drawString(g, "Start Turn", handler.getWidth() / 2, handler.getHeight() / 2,
                true, Color.white, Assets.font20);

        switchingTurn = true;
    }

    private void startTurnAfterClick() {

        //waits for mouse click before starting turn
        Timer timer = new Timer(100, (ActionEvent e) -> {
            if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {
                switchingTurn = false;
                clock.ticking = true;
                clock = new Clock(handler);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void rotateTurns() {
        
        for (int i = 0; i < turn.length; i++) {
            if (turn[i] == true) {
                turn[i] = false;
                if (i + 1 >= turn.length) {
                    turn[0] = true;
                } else {
                    turn[i + 1] = true;
                }
                break;
            }
        }
    }

    private void startOfTurnMana() {
        
        Player p = handler.getPlayersTurn();
        startTurnMana = 180 + (p.getHouses().size() * 25)
                + (p.getPortals().size() * 50)
                + (p.getTowers().size() * 75);
        handler.getPlayersTurn().addMana(startTurnMana);
    }

    private void incrementTurnsPlayed() {
        
        if (turn[0]) {
            turnsplayed++;
        }
    }

    private void moveCamera() {
        
        int x, y;
        if (handler.getPlayersTurn() == handler.getPlayer(0)){
            x = -40;
            y = -40;
        } else {
            x = 400;
            y = 400;
        }
        handler.getGameCamera().getCamera().setX(x);
        handler.getGameCamera().getCamera().setY(y);
    }

}
