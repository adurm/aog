package aog2.game.camera;

import aog2.game.helpers.Handler;

/**
 *
 * @author adam
 *
 * Class GameCamera gets and sets the Camera and keeps track of its location
 */
public class GameCamera {

    private int xOffset, yOffset;
    private Camera camera;
    private Handler handler;

    public GameCamera(Handler handler, int xOffset, int yOffset) {

        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnCamera(Camera c) {

        camera = c;
        xOffset = c.getX();
        yOffset = c.getY();
    }

    public void move(int x, int y) {

        xOffset += x;
        yOffset += y;

    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public Camera getCamera() {
        return camera;
    }

}
