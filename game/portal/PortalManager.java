package aog2.game.portal;

import aog2.game.helpers.Handler;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * @author adam
 * 
 * PortalManager is responsible for ticking and rendering all interfaces
 */
public class PortalManager {

    private Handler handler;
    private ArrayList<Portal> portals;

    public PortalManager(Handler handler) {
        this.handler = handler;
        portals = new ArrayList<>();
    }

    public void tick() {
        
        for (int x = 0; x < portals.size(); x++) {
            Portal p = portals.get(x);
            p.tick();
        }
    }

    public void render(Graphics g) {

        for (Portal p : portals) {
            p.render(g);
        }
    }

    public void addPortal(Portal p) {
        portals.add(p);
    }

    public ArrayList<Portal> getPortals() {
        return portals;
    }

    public void setPortals(ArrayList<Portal> portals) {
        this.portals = portals;
    }

}
