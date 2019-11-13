package aog2.game.capturing;

import aog2.game.helpers.Handler;
import aog2.game.players.Player;
import aog2.game.tiles.Tile;
import aog2.game.units.Unit;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class StructureCapturing {

    public static ArrayList<StructureCapturing> structCaps = new ArrayList<>(256);

    private final Handler handler;
    private Tile tile;
    private final Unit unit;
    private final int xTile, yTile;
    private final static float housecap = 10, towercap = 15, portcap = 10;
    private float capturePercentage = 0;

    public StructureCapturing(Handler handler, Tile tile, Unit unit) {

        this.handler = handler;
        this.tile = tile;
        this.unit = unit;
        this.xTile = tile.getX();
        this.yTile = tile.getY();
    }

    public static void tick() {

        for (Iterator<StructureCapturing> iterator = structCaps.iterator(); iterator.hasNext();) {
            StructureCapturing s = iterator.next();
            if (s.unit.getCurrentHP() <= 0){ //if unit dies while capping, stop the cap
                iterator.remove();
            }
            if (s.capturePercentage >= 40.0) { //if fully capped
                if (s.tile.getId() == 21
                        || s.tile.getId() == 22
                        || s.tile.getId() == 23) { //if tile is portal
                    if (s.unit.getOwner() == s.getHandler().getPlayer(0)) { //if its blues unit
                        s.getHandler().getPlayers()[1].removePortal(s.tile);
                        s.getHandler().getMap().changeTile(s.xTile, s.yTile, Tile.bluePortalTile, s.unit);
                    } else if (s.unit.getOwner() == s.getHandler().getPlayer(1)) { //if reds unit
                        s.getHandler().getPlayers()[0].removePortal(s.tile);
                        s.getHandler().getMap().changeTile(s.xTile, s.yTile, Tile.redPortalTile, s.unit);
                    }
                    s.tile = s.getHandler().getMap().getTileAt(s.xTile, s.yTile);
                }
                s.tile.setOwner(s.unit.getOwner());
                s.unit.getOwner().addStructure(s.tile);
                iterator.remove();
            }
        }
    }

    public static void render(Graphics g) {

        for (StructureCapturing s : structCaps) {

            if (s.tile.getOwner() != null){
                g.setColor(s.getHandler().getMap().getTileAt(s.xTile, s.yTile).getOwner().getColor());
            } else {
                g.setColor(Color.black);
            }
            
            g.fillRect(s.getHandler().getPosition().fixToLocWithXCoord(s.xTile),
                    s.getHandler().getPosition().fixToLocWithYCoord(s.yTile) + Tile.TILEHEIGHT - 2, Tile.TILEWIDTH, 7);

            g.setColor(Color.white);
            g.drawRect(s.getHandler().getPosition().fixToLocWithXCoord(s.xTile),
                    s.getHandler().getPosition().fixToLocWithYCoord(s.yTile) + Tile.TILEHEIGHT - 2, Tile.TILEWIDTH, 7);

            g.setColor(s.unit.getOwner().getColor());
            g.fillRect(s.getHandler().getPosition().fixToLocWithXCoord(s.xTile),
                    s.getHandler().getPosition().fixToLocWithYCoord(s.yTile) + Tile.TILEHEIGHT - 2, (int) s.capturePercentage, 7);
        }
    }

    public static void incrementCapturePercentage(Player playersturn) {

        for (StructureCapturing s : structCaps) {
            if (playersturn.equals(s.unit.getOwner())) {
                float progress;
                float health = s.unit.getCurrentHP();
                if ("Tower".equals(s.tile.getName())) {
                    progress = health / towercap * Tile.TILEWIDTH;
                } else {
                    progress = health / housecap * Tile.TILEWIDTH;
                }
                s.capturePercentage += progress;
            }
        }
    }

    public static void addStrCap(StructureCapturing s) {
        structCaps.add(s);
    }

    public Handler getHandler() {
        return handler;
    }

}
