package aog2.game.turns;

import aog2.game.graphics.Assets;
import aog2.game.helpers.Handler;
import aog2.game.helpers.Text;
import aog2.game.players.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author adam
 */
public class WinConditions {

    private Handler handler;
    private ArrayList<Player> players;
    public static String winner = "";

    public WinConditions(Handler handler) {
        
        this.handler = handler;
        
        players = new ArrayList<>(handler.getPlayers().length);
        for (Player p : handler.getPlayers()) {
            players.add(p);
        }
    }

    public void tick() {

        for (Iterator<Player> iterator = players.iterator(); iterator.hasNext();) {
            Player p = iterator.next();
            if ((p.getPortals().isEmpty() && !p.hasPeasentsOrChief())) {
                iterator.remove();
            }
            if (players.size() == 1) {
                if (players.contains(handler.getPlayer(0))) {
                    winner = "Blue wins!";
                }
                if (players.contains(handler.getPlayer(1))) {
                    winner = "Red wins!";
                }

            }
        }
    }

    public void render(Graphics g) {

        if (!"".equals(winner)){
            g.setColor(Color.black);
            g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
            Text.drawString(g, winner, handler.getWidth()/2, handler.getHeight()/2,
                    true, Color.white, Assets.font20);
        }
    }

}
