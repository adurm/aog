package aog2.game.movement;

import aog2.game.helpers.Handler;
import aog2.game.tiles.Tile;
import aog2.game.units.Unit;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author adam
 */
public class RangeFinder {

    private Handler handler;
    private double[][] costs;

    public RangeFinder(Handler handler) {
        
        this.handler = handler;
        
        costs = new double[handler.getMap().getWidth()][handler.getMap().getHeight()];
        for (double[] cost : costs) {
            for (int j = 0; j < costs[0].length; j++) {
                cost[j] = 0;
            }
        }
    }

    public ArrayList<Tile> findMoveRange(Tile startTile, double speed) {

        PriorityQueue<Tile> open = new PriorityQueue();
        ArrayList<Tile> closed = new ArrayList<>();

        open.add(startTile);

        while (!open.isEmpty()) {

            Tile t = open.remove();
            closed.add(t);

            if (costs[t.getX()][t.getY()] < speed) {
                for (Tile neighbour : getNeighbours(t)) {
                    double newCost = costs[t.getX()][t.getY()] + moveCost(t, neighbour);
                    if (costs[neighbour.getX()][neighbour.getY()] == 0
                            || newCost < costs[neighbour.getX()][neighbour.getY()]) {
                        costs[neighbour.getX()][neighbour.getY()] = newCost;
                        if (!open.contains(neighbour)
                                && neighbour.getUnit() == null) {
                            open.add(neighbour);
                        }
                    }
                }
            }
        }

        ArrayList<Tile> results = new ArrayList<>();
        for (Tile t : closed) {
            if (costs[t.getX()][t.getY()] <= speed
                    && costs[t.getX()][t.getY()] > 0
                    && !results.contains(t)) {
                results.add(t);
            }
            costs[t.getX()][t.getY()] = 0;
        }
        return results;
    }

    private ArrayList<Tile> getNeighbours(Tile X) {

        ArrayList<Tile> neighbours = new ArrayList<>();

        //Left up right down
        if (X.getX() - 1 >= 1) {
            neighbours.add(handler.getMap().getTileAt(X.getX() - 1, X.getY()));
        }
        if (X.getY() - 1 >= 1) {
            neighbours.add(handler.getMap().getTileAt(X.getX(), X.getY() - 1));
        }
        if (X.getX() + 1 <= handler.getMap().getWidth() - 1) {
            neighbours.add(handler.getMap().getTileAt(X.getX() + 1, X.getY()));
        }
        if (X.getY() + 1 <= handler.getMap().getHeight() - 1) {
            neighbours.add(handler.getMap().getTileAt(X.getX(), X.getY() + 1));
        }
        return neighbours;
    }

    private double moveCost(Tile t, Tile neighbour) {

        double a = t.getSpeed();
        double b = neighbour.getSpeed();

        return Math.max(a, b);
    }

    public ArrayList<Tile> attackRange(Tile startTile, Unit unit) {

        ArrayList<Tile> results = new ArrayList<>();
        for (int i = startTile.getX() - unit.getMaxRange(); i <= startTile.getX() + unit.getMaxRange(); i++) {
            for (int j = startTile.getY() - unit.getMaxRange(); j <= startTile.getY() + unit.getMaxRange(); j++) {
                if (i == startTile.getX() && j == startTile.getY()) {
                    continue;
                }
                if (validAttack(startTile.getX(), i, startTile.getY(), j, unit.getMaxRange())
                        && !validAttack(startTile.getX(), i, startTile.getY(), j, unit.getMinRange() - 1)
                        && !results.contains(handler.getMap().getTileAt(i, j))) {
                    results.add(handler.getMap().getTileAt(i, j));
                }
            }
        }
        return results;
    }

    public ArrayList<Tile> attackAndMoveRange(Tile startTile, Unit unit) {

        ArrayList<Tile> results = new ArrayList<>();
        for (Tile t : findMoveRange(startTile, unit.getSpeed())) {
            for (int i = t.getX() - unit.getMaxRange(); i <= t.getX() + unit.getMaxRange(); i++) {
                for (int j = t.getY() - unit.getMaxRange(); j <= t.getY() + unit.getMaxRange(); j++) {
                    if (i == t.getX() && j == t.getY()) {
                        continue;
                    }
                    if (validAttack(t.getX(), i, t.getY(), j, unit.getMaxRange())
                            && !validAttack(t.getX(), i, t.getY(), j, unit.getMinRange() - 1)
                            && !results.contains(handler.getMap().getTileAt(i, j))) {
                                results.add(handler.getMap().getTileAt(i, j));
                    }
                }
            }
        }

        return results;
    }

    private boolean validAttack(int xs, int xe, int ys, int ye, int range) {
        return Math.abs((xs - xe)) + Math.abs((ys - ye)) <= range
                && xe < handler.getMap().getWidth() - 1
                && xe > 0
                && ye > 0
                && ye < handler.getMap().getHeight() - 1;
    }

}
