package aog2.game.movement;

import aog2.game.attacks.AttackManager;
import aog2.game.helpers.Handler;
import aog2.game.interfaces.Interface;
import aog2.game.tiles.Tile;
import aog2.game.capturing.StructureCapturing;
import aog2.game.units.Unit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam class MoveRange handles displaying the reachable tiles (both by
 * attacking and moving)
 */
public class MoveRange extends Movement {

    private AttackManager attackManager;

    private Tile newTile, prevTile;
    private Unit newUnit, prevUnit;

    private int xCoord, yCoord;
    private int prevxCoord, prevyCoord;
    private int speed;

    public MoveRange(Handler handler) {
        super(handler);
        attackManager = new AttackManager(handler);
        rangeFinder = new RangeFinder(handler);
    }

    @Override
    public void tick() {

        StructureCapturing.tick();
        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {

            xCoord = handler.getPosition().getCoordX();
            yCoord = handler.getPosition().getCoordY();

            if (xCoord > handler.getMap().getWidth() - 2
                    || xCoord < 1
                    || yCoord < 1
                    || yCoord > handler.getMap().getHeight() - 2) {
                return;
            }

            saveTileInfo();
            handleUnitSelection();

            prevxCoord = xCoord;
            prevyCoord = yCoord;
        }
        

    }

    @Override
    public void render(Graphics g) {

        StructureCapturing.render(g);
        attackManager.render(g);
        //if clicking outside of map do nothing
        if (xCoord > handler.getMap().getWidth() - 2
                || xCoord < 1
                || yCoord < 1
                || yCoord > handler.getMap().getHeight() - 2) {
            return;
        }

        if (unitSelected != null) {
            showMoveRange(g);
        }
    }

    private void handleUnitSelection() {

        if (newUnit != null) { //if you click on  a unit
            if (unitSelected == null) { //if nothing is already selected
                unitSelected = newUnit; //select the unit
            } else { //if a unit is selected
                if (prevTile == newTile) { //if clicking on the same unit twice
                    unitSelected = null; //deselect the unit
                } else { // if clicking on another unit
                    if (prevUnit.getOwner() == newUnit.getOwner()) { //if selecting another friendly unit
                        unitSelected = newUnit; //select the unit
                    } else { //clicking an enemy unit
                        if (rangeFinder.attackRange(prevTile, prevUnit)
                                .contains(newTile) && prevUnit.isActive()) { //if in attack range
                            attack();
                        } else { //cant attack
                            unitSelected = null; //unselect
                        }
                    }
                }
            }
        } else { //if you're not clicking on a unit
            if (unitSelected != null) { //you're attempting to move a unit
                if (rangeFinder.findMoveRange(prevTile, speed).contains(newTile)) {
                    if (!prevUnit.isActive() || prevUnit.hasMoved()) {
                        unitSelected = null;
                    } else { //move unit
                        checkForStructure();
                        moveUnit();
                    }
                } else { //dont move the unit because you're clicking outside of its range
                    unitSelected = null;
                }
            } else { //you're not doing anything unit related
                unitSelected = null;
            }
        }
    }

    private void saveTileInfo() {

        newTile = handler.getMap().getTileAt(xCoord, yCoord);
        newTile.setX(xCoord);
        newTile.setY(yCoord);
        try {
            prevTile = handler.getMap().getTileAt(prevxCoord, prevyCoord);
            prevUnit = prevTile.getUnit();
        } catch (NullPointerException e) {
        }
        saveUnitInfo();
    }

    private void saveUnitInfo() {

        try {
            newUnit = newTile.getUnit();
            speed = newUnit.getSpeed();
        } catch (NullPointerException e) {
        }

    }

    private void attack() {

        Interface.unitInfoOpen = false;
        unitSelected = null;
        prevUnit.setHasAttacked(true);
        attackManager.calculateAttackDamage(
                prevTile, prevUnit, prevxCoord, prevyCoord,
                newTile, newUnit, xCoord, yCoord);
    }

    private void checkForStructure() {

        if (newTile.isStructure()
                && ("Peasant".equals(prevUnit.getName())
                || "Chief".equals(prevUnit.getName()))
                && (newTile.getOwner() == null
                || newTile.getOwner() != handler.getPlayersTurn())) {
            StructureCapturing cap = new StructureCapturing(handler, newTile, prevUnit);
            StructureCapturing.structCaps.add(cap);
        }
    }

    private void moveUnit() {

        prevTile.removeUnit();
        newTile.addUnit(prevUnit);
        prevUnit.setHasMoved(true);
        unitSelected = null;

    }

    private void showMoveRange(Graphics g) {

        if (unitSelected.getOwner() == handler.getPlayersTurn()
                && !unitSelected.hasAttacked()) { //your unit selected
            if (unitSelected.hasMoved()) { //show attack range
                for (Tile t : rangeFinder.attackRange(newTile, newUnit)) {
                    g.setColor(new Color(255, 0, 0, 80));
                    g.fillRect(handler.getPosition().fixToLocWithXCoord(t.getX()),
                            handler.getPosition().fixToLocWithYCoord(t.getY()),
                            Tile.TILEWIDTH, Tile.TILEHEIGHT);
                }
            } else {
                for (Tile t : rangeFinder.attackAndMoveRange(newTile, newUnit)) {
                    if (!rangeFinder.findMoveRange(newTile, newUnit.getSpeed()).contains(t)) {
                        g.setColor(new Color(255, 0, 0, 100));
                        g.fillRect(handler.getPosition().fixToLocWithXCoord(t.getX()),
                                handler.getPosition().fixToLocWithYCoord(t.getY()),
                                Tile.TILEWIDTH, Tile.TILEHEIGHT);
                    }

                }
                for (Tile t : rangeFinder.findMoveRange(newTile, newUnit.getSpeed())) {
                    g.setColor(new Color(0, 255, 0, 60));
                    g.fillRect(handler.getPosition().fixToLocWithXCoord(t.getX()),
                            handler.getPosition().fixToLocWithYCoord(t.getY()),
                            Tile.TILEWIDTH, Tile.TILEHEIGHT);
                }
            }
        } else { //enemy unit selected
            for (Tile t : rangeFinder.attackAndMoveRange(newTile, newUnit)) {
                g.setColor(new Color(255, 0, 0, 100));
                g.fillRect(handler.getPosition().fixToLocWithXCoord(t.getX()),
                        handler.getPosition().fixToLocWithYCoord(t.getY()),
                        Tile.TILEWIDTH, Tile.TILEHEIGHT);

            }
        }
    }

}
