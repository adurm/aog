package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * @author adam
 * 
 * InterfaceManager is responsible for ticking and rendering all interfaces
 */
public class InterfaceManager {

    private Handler handler;
    private ArrayList<Interface> interfaces;

    public InterfaceManager(Handler handler) {
        this.handler = handler;
        interfaces = new ArrayList<>();
    }

    public void tick() {
        for (int x = 0; x < interfaces.size(); x++) {
            Interface i = interfaces.get(x);
            i.tick();
        }
    }

    public void render(Graphics g) {
        for (Interface i : interfaces) {
            i.render(g);
        }
    }

    public void addInterface(Interface i) {
        interfaces.add(i);
    }
    
    public ArrayList<Interface> getInterfaces(){
        return interfaces;
    }

}
