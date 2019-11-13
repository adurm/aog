package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author adam
 *
 * Class Clock keeps track of the turn time
 */
public class Clock extends Interface {

    public static boolean ticking;
    
    private final int x = 10, y = 10;
    private final int width = 100;
    private final int height = 100;
    private final int centerOfClockX = x + width / 2 - 8;
    private final int centerOfClockY = x + height / 2 - 8;
    private double hand = 2;
    private int tx;
    private int ty;

    public Clock(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        if (tx == 60 && ty == 13) {
            ticking = false;
        }

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.clock2, x, y, null);

        if (!ticking) {
            turnManager.switchTurn();
            turnManager.showTurnStart(g);
        }

        double newhand = (hand + 0.01) % 60; // 0.01 means 2 minutes turn time
        double minsecdeg = (double) Math.PI / 30; // (/30) controls speed
        hand = newhand;

        tx = centerOfClockX + (int) (45 * Math.sin((hand * minsecdeg)));
        ty = centerOfClockY - (int) (40 * Math.cos((hand * minsecdeg)));

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(centerOfClockX + 8, centerOfClockY + 8, tx, ty);
        g2d.setStroke(new BasicStroke(1));
    }

}
