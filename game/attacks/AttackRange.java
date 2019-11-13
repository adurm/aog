package aog2.game.attacks;

import aog2.game.helpers.Handler;
import aog2.game.movement.Movement;
import aog2.game.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam class AttackRange handles showing the attack of a unit when
 * hovering over tiles
 */
public class AttackRange extends Movement {

    //private boolean unitclickedon;
    private int xCoord, yCoord;
    private int maxrange, minrange;
    private int onlyonce;
    private int prevxCoord, prevyCoord;

    public AttackRange(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

        xCoord = handler.getPosition().getCoordX();
        yCoord = handler.getPosition().getCoordY();

        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {

            //if clicking outside of map do nothing
            if (xCoord > handler.getMap().getWidth() - 2
                    || xCoord < 1
                    || yCoord < 1
                    || yCoord > handler.getMap().getHeight() - 2) {
                return;
            }

            handleClicks();

            prevxCoord = xCoord;
            prevyCoord = yCoord;
        }
    }

    @Override
    public void render(Graphics g) {

        if (unitSelected != null
                && !unitSelected.hasMoved()
                && unitSelected.isActive()) {

            maxrange = unitSelected.getMaxRange();
            minrange = unitSelected.getMinRange();

            for (int i = xCoord - maxrange; i <= xCoord + maxrange; i++) {
                for (int j = yCoord - maxrange; j <= yCoord + maxrange; j++) {
                    if (i == xCoord && j == yCoord
                            || (i > handler.getMap().getWidth() - 2
                            || i < 1
                            || j < 1
                            || j > handler.getMap().getHeight() - 2)) {
                        continue;
                    }
                    if (validAttack(xCoord, i, yCoord, j, maxrange) //within range
                            && !validAttack(xCoord, i, yCoord, j, minrange - 1)) {
                        g.setColor(new Color(255, 0, 0, 100));
                        g.fillRect(handler.getPosition().fixToLocWithXCoord(i),
                                handler.getPosition().fixToLocWithYCoord(j),
                                Tile.TILEWIDTH, Tile.TILEHEIGHT);
                    }
                }
            }
        }
    }

    private void handleClicks() {

        if (handler.getMap().getTileAt(xCoord, yCoord).getUnit() != null) {

            if (onlyonce++ == 0) {
                //unitclickedon = true; //youve clicked a unit
                prevxCoord = xCoord;
                prevyCoord = yCoord;
                return;
            }
            if (prevxCoord == xCoord && prevyCoord == yCoord) {
                //unitclickedon = !unitclickedon; //ur unclicking the unit
                onlyonce = 0;
            } else {
                //unitclickedon = true; //ur clicking a diff unit
                onlyonce = 0;
            }
        } else {
            //unitclickedon = false;
        }
    }

    private boolean validAttack(int xs, int xe, int ys, int ye, int range) {
        return Math.abs((xs - xe)) + Math.abs((ys - ye)) <= range
                && xe < handler.getMap().getWidth() - 1
                && xe > 0
                && ye > 0
                && ye < handler.getMap().getHeight() - 1;
    }

}
