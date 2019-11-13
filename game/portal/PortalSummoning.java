package aog2.game.portal;

import aog2.game.helpers.Handler;
import aog2.game.units.Unit;
import aog2.game.units.units.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam
 */
public class PortalSummoning extends Portal {

    public PortalSummoning(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        if (portalOpen && handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {

            int xCoord = handler.getPosition().getCoordX();
            int yCoord = handler.getPosition().getCoordY();

            if (xCoord == portalx && yCoord == portaly - 1) {
                buyUnit("Peasant", 50);
            } else if (xCoord == portalx + 1 && yCoord == portaly) {
                buyUnit("Archer", 200);
            } else if (xCoord == portalx - 1 && yCoord == portaly) {
                buyUnit("Knight", 250);
            } else if (xCoord == portalx && yCoord == portaly + 1) {
                buyUnit("Wizard", 250);
            }
        }

    }

    @Override
    public void render(Graphics g) {
    }

    private void buyUnit(String unitName, int cost) {

        if (!owner.removeMana(cost)) {
            return;
        }

        Unit u;

        if (null == unitName) {
            return;
        } else switch (unitName) {
            case "Peasant":
                u = new Peasant(handler, owner);
                break;
            case "Archer":
                u = new Archer(handler, owner);
                break;
            case "Knight":
                u = new Knight(handler, owner);
                break;
            case "Wizard":
                u = new Wizard(handler, owner);
                break;
            default:
                return;
        }
        handler.getMap().addUnit(u, portalx, portaly);
        handler.getMap().getTileAt(portalx, portaly).addUnit(u);
        portalOpen = false;
    }

}
