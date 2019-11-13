package aog2.game.movement;

import aog2.game.helpers.Handler;
import aog2.game.units.Unit;
import java.awt.Graphics;

/**
 *
 * @author adam Abstract class Movement All calculations relating to unit
 * movement extend this class
 */
public abstract class Movement {

    protected Handler handler;
    public static boolean attacking, retaliating;
    public static Unit unitSelected;
    public static RangeFinder rangeFinder;

    public Movement(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    

}
