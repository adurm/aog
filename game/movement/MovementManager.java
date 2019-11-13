package aog2.game.movement;

import aog2.game.helpers.Handler;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * @author adam
 * class MovementManager ticks and renders all movement classes
 */
public class MovementManager {

    private Handler handler;
    private ArrayList<Movement> movements;

    public MovementManager(Handler handler) {
        this.handler = handler;
        movements = new ArrayList<>();
    }

    public void tick() {
        
        for (int x = 0; x < movements.size(); x++) {
            Movement m = movements.get(x);
            m.tick();
        }
    }

    public void render(Graphics g) {

        movements.forEach((m) -> {
            m.render(g);
        });
    }

    public void addMovement(Movement m) {
        movements.add(m);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Movement> getMovements() {
        return movements;
    }

    public void setMovements(ArrayList<Movement> movements) {
        this.movements = movements;
    }

}
