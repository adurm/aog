package aog2.states;

import aog2.game.helpers.Handler;
import java.awt.Graphics;

/**
 * 
 * @author adam
 * abstract class State is the current state of the game
 * States to include:
 *  - main menu state
 *  - lobby state
 *  - game lobby state
 *  - game state
 *  - game over state
 */
public abstract class State {

    private static State currentState = null;
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
