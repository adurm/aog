package aog2.game.tiles;

import aog2.game.graphics.Assets;
import aog2.game.helpers.Handler;
import aog2.game.maps.Map;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author adam
 */
public class Bitmasking {

    private Map map;
    private Handler handler;

    public Bitmasking(Handler handler, Map map) {
        this.map = map;
        this.handler = handler;
    }

    public BufferedImage getImage(Tile tile) {
        int value = 0;
        int id = tile.getId();

        if (tile.getY() > 0) { //up
            if (map.getTileAt(tile.getX(), tile.getY() - 1).getId() == id
                    || map.getTileAt(tile.getX(), tile.getY() - 1).getId() == 99
                    || map.getTileAt(tile.getX(), tile.getY() - 1).isPortal()) {
                value += 1;
            }
        }
        if (tile.getX() < map.getWidth() - 2) { //right
            if (map.getTileAt(tile.getX() + 1, tile.getY()).getId() == id
                    || map.getTileAt(tile.getX() + 1, tile.getY()).getId() == 99
                    || map.getTileAt(tile.getX() + 1, tile.getY()).isPortal()) {
                value += 2;

            }
        }
        if (tile.getY() < map.getHeight() - 1) {//down

            if (map.getTileAt(tile.getX(), tile.getY() + 1).getId() == id
                    || map.getTileAt(tile.getX(), tile.getY() + 1).getId() == 99
                    || map.getTileAt(tile.getX(), tile.getY() + 1).isPortal()) {
                value += 4;
            }
        }
        if (tile.getX() > 0) {
            if (map.getTileAt(tile.getX() - 1, tile.getY()).getId() == id
                    || map.getTileAt(tile.getX() - 1, tile.getY()).getId() == 99
                    || map.getTileAt(tile.getX() - 1, tile.getY()).isPortal()) {
                value += 8;
            }
        }

        if (tile.getId() == 10) {
            return Assets.opentile;
        }
        if (tile.getId() == 11) {
            return Assets.roadtiles[value];
        }
        if (tile.getId() == 12) {
            Random r = new Random();
            return Assets.rocktiles[r.nextInt(2)];
        }
        if (tile.getId() == 13) {
            return Assets.mudtiles[value];
        }
        if (tile.getId() == 14) {
            return Assets.rivertiles[value];
        }
        if (tile.getId() == 15) {
            return Assets.seatiles[value];
        }
        if (tile.getId() == 16) {
            return Assets.treetile;
        }
        if (tile.getId() == 17) {
            return Assets.houseopentile;
        }
        if (tile.getId() == 18) {
            return Assets.houseroadtile;
        }
        if (tile.getId() == 19) {
            return Assets.toweropentile;
        }
        if (tile.getId() == 20) {
            return Assets.towerroadtile;
        }
        if (tile.getId() == 21) {
            return Assets.blueportalroadtile;
        }
        if (tile.getId() == 22) {
            return Assets.redportalroadtile;
        }
        if (tile.getId() == 23) {
            return Assets.emptyportalroadtile;
        } else {
            return Assets.bordertile;
        }

    }

}
