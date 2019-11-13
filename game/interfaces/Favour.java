//package aog2.game.interfaces;
//
//import aog2.game.helpers.Handler;
//import aog2.game.graphics.Assets;
//import aog2.game.tiles.Tile;
//import java.awt.Graphics;
//
///**
// *
// * @author adam
// *
// * Class Favour displays the favour level for factions at the bottom of the
// * screen
// */
//public class Favour extends Interface {
//
//    private final int width = Tile.TILEWIDTH, height = Tile.TILEHEIGHT;
//    private int x = (handler.getWidth() - width) / 2;
//    private int y = handler.getHeight() - height - (height/4);
//
//    public Favour(Handler handler) {
//        super(handler);
//    }
//
//    @Override
//    public void tick() {
//    }
//
//    @Override
//    public void render(Graphics g) {
//
//        g.drawImage(Assets.blue, x - (width * 3 / 2), y, width, height, null);
//        g.drawImage(Assets.red, x, y, width, height, null);
//        g.drawImage(Assets.green, x + (width * 3 / 2), y, width, height, null);
//    }
//
//}
