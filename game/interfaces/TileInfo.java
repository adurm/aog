package aog2.game.interfaces;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import static aog2.game.interfaces.Interface.unitInfoOpen;
import aog2.game.tiles.Tile;
import aog2.game.units.Unit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TileInfo extends Interface {

    private final int width = 300, height = 80;
    private final int x = handler.getWidth()/2 - width/2, y = 0;
    

    private int mousex, mousey;
    private int tilexLoc, tileyLoc;
    private int prevTilexLoc, prevTileyLoc;
    private boolean hover;

    public TileInfo(Handler handler) {
        super(handler);
    }

    public void tick() {

        //keep track of mouse and tile the mouse is on
        mousex = handler.getMouseManager().getMouseX();
        mousey = handler.getMouseManager().getMouseY();
        tilexLoc = handler.getPosition().getCoordX();
        tileyLoc = handler.getPosition().getCoordY();

        if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {

            //if clicking outside of map or on an interface do nothing
            if (tilexLoc > handler.getMap().getWidth() - 2
                    || tilexLoc < 1
                    || tileyLoc < 1
                    || tileyLoc > handler.getMap().getHeight() - 2
                    || mousex < 225 && mousey < 53
                    || mousex < 115 && mousey < 110
                    || mousex < 100 && mousey < 435 && mousey > 315) {
                return;
            }

            //manages which info bar should show according to clicks
            if (handler.getMap().getUnit(tilexLoc, tileyLoc) != null) {
                if (tileInfoOpen) {
                    tileInfoOpen = false;
                    unitInfoOpen = true;
                } else if (unitInfoOpen) {
                    if (prevTilexLoc == tilexLoc
                            && prevTileyLoc == tileyLoc) {
                        tileInfoOpen = true;
                        unitInfoOpen = false;
                    }
                } else {
                    tileInfoOpen = false;
                    unitInfoOpen = true;
                }
            } else {
                unitInfoOpen = false;
                if (tileInfoOpen) {
                    tileInfoOpen = !(prevTilexLoc == tilexLoc
                            && prevTileyLoc == tileyLoc);
                } else {
                    tileInfoOpen = true;
                }
            }

            //record previous click location
            prevTilexLoc = tilexLoc;
            prevTileyLoc = tileyLoc;
        }

        //if mouse is over this interface
        hover = mousex <= x + width
                && mousex >= x
                && mousey <= y + height
                && mousey >= y
                && (tileInfoOpen || unitInfoOpen);
    }

    @Override
    public void render(Graphics g) {

        Tile t = handler.getMap().getTileAt(prevTilexLoc, prevTileyLoc);
        Unit u = t.getUnit();

        if (unitInfoOpen && u != null) {
            showUnitInfo(g, u);
            if (hover) {
                showUnitHoverInfo(g, u);
            }
        } else if (tileInfoOpen) {
            showTileInfo(g, t);
            if (hover) {
                showTileHoverInfo(g, t);
            }
        }
    }

    private void showTileInfo(Graphics g, Tile t) {

        g.drawImage(Assets.tileInfo2, x, y, null);
        if (t.getOwner() == handler.getPlayer(0)) {
            g.drawImage(Assets.blueflag, x + 7, y + 8, 17, 17, null);
        } else if (t.getOwner() == handler.getPlayer(1)) {
            g.drawImage(Assets.redflag, x + 7, y + 8, 17, 17, null);
        }
        Text.drawString(g, t.getName(), x + 42, y + 25, false, Color.white, Assets.font20);
        if ("Road".equals(t.getName())) {
            Text.drawString(g, "2/3", x + 242, y + 15, true, Color.white, Assets.font9);
        } else {
            Text.drawString(g, Integer.toString((int) t.getSpeed()), x + 240, y + 16, true, Color.white, Assets.font16);
        }
        Text.drawString(g, Integer.toString(t.getMana()), x + 45, y + 45, true, Color.white, Assets.font16);
        Text.drawString(g, "" + t.getDefence() + "%", x + 280, y + 30, true, Color.white, Assets.font16);

    }

    private void showTileHoverInfo(Graphics g, Tile t) {

        g.setColor(Color.black);

        if (mousex > x + 4
                && mousey > y + 5
                && mousex < x + 29
                && mousey < y + 30) {
            g.fillRect(mousex, mousey + 10, 100, 20);
            String s = "Nobody owns this tile";
            if (t.getOwner() == handler.getPlayer(0)) {
                s = "Property of Blue";
            } else if (t.getOwner() == handler.getPlayer(1)) {
                s = "Property of Red";
            }
            Text.drawString(g, s, mousex + 50, mousey + 20, true, Color.white, Assets.font9);
        } else if (mousex > x + 229
                && mousey > y + 5
                && mousex < x + 253
                && mousey < y + 30) {
            g.fillRect(mousex, mousey + 10, 90, 20);
            String s = "Speed cost: " + t.getSpeed();
            if ("Road".equals(t.getName())) {
                s = "Speed cost: 2/3";
            }
            Text.drawString(g, s, mousex + 45, mousey + 20, true, Color.white, Assets.font9);
        } else if (mousex > x + 4
                && mousey > y + 34
                && mousex < x + 59
                && mousey < y + 59) {
            g.fillRect(mousex, mousey + 10, 100, 20);
            Text.drawString(g, "Tile produces " + t.getMana() + " mana", mousex + 50, mousey + 20, true, Color.white, Assets.font9);
        } else if (mousex > x + 261
                && mousey > y + 17
                && mousex < x + 293
                && mousey < y + 48) {
            g.fillRect(mousex, mousey + 10, 130, 20);
            Text.drawString(g, "Tile provides " + t.getDefence() + "% defence", mousex + 65, mousey + 20, true, Color.white, Assets.font9);
        }

    }

    private void showUnitInfo(Graphics g, Unit u) {

        g.drawImage(Assets.unitinfo2, x, y, null);
        g.drawImage(u.getTypeImg(), x + 8, y + 8, 20, 20, null);
        Text.drawString(g, u.getName(), x + 44, y + 25, false, Color.white, Assets.font20);
        Text.drawString(g, "" + u.getCurrentHP() + "/" + u.getMaxHP(), x + 267, y + 17, true, Color.white, Assets.font16);
        if (u.getMinRange() == u.getMaxRange()) {
            Text.drawString(g, "" + u.getMinRange(), x + 23, y + 46, true, Color.white, Assets.font16);
        } else {
            Text.drawString(g, "" + u.getMinRange() + "-" + u.getMaxRange(), x + 23, y + 46, true, Color.white, Assets.font16);
        }
        BufferedImage img = Assets.greenmove;
        if (u.hasAttacked()) {
            img = Assets.redmove;
        } else if (u.hasMoved()) {
            img = Assets.yellowmove;
        }
        g.drawImage(img, x + 45, y + 36, 20, 20, null);

    }

    private void showUnitHoverInfo(Graphics g, Unit u) {

        g.setColor(Color.black);

        if (mousex > x + 5
                && mousey > y + 5
                && mousex < x + 30
                && mousey < y + 30) {
            g.fillRect(mousex, mousey + 10, 60, 20);
            Text.drawString(g, u.getType().toString(), mousex + 30, mousey + 20, true, Color.white, Assets.font9);
        } else if (mousex > x + 239
                && mousey > y + 5
                && mousex < x + 294
                && mousey < y + 30) {
            g.fillRect(mousex, mousey + 10, 60, 20);
            Text.drawString(g, "Health", mousex + 30, mousey + 20, true, Color.white, Assets.font9);
        } else if (mousex > x + 5
                && mousey > y + 33
                && mousex < x + 40
                && mousey < y + 59) {
            g.fillRect(mousex, mousey + 10, 66, 20);
            Text.drawString(g, "Attack Range", mousex + 33, mousey + 20, true, Color.white, Assets.font9);
        } else if (mousex > x + 43
                && mousey > y + 33
                && mousex < x + 68
                && mousey < y + 59) {
            g.fillRect(mousex, mousey + 10, 100, 20);
            String s = "Can move and attack";
            if (u.hasAttacked()) {
                s = "Cannot be used";
            } else if (u.hasMoved()) {
                s = "Can still attack";
            }
            Text.drawString(g, s, mousex + 50, mousey + 20, true, Color.white, Assets.font9);
        }
    }

}
