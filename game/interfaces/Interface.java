
package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.turns.TurnManager;
import java.awt.Graphics;

/**
 * 
 * @author adam
 * 
 * Abstract interface class
 * All interfaces extend this class
 */
public abstract class Interface {

    protected Handler handler;
    public static boolean tileInfoOpen, unitInfoOpen;
    static boolean mapOpen;
    public static boolean menuOpen;
    static TurnManager turnManager;
    
    public Interface(Handler handler){
        this.handler = handler;
    }
    
    public abstract void tick();

    public abstract void render(Graphics g);

}
