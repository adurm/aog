
package aog2.game.camera;

import aog2.game.helpers.Handler;
import aog2.game.tiles.Tile;

/**
 * 
 * @author adam
 * 
 * Class Camera is controlled by the user and displays a certain area of the map
 */
public class Camera {
    
    //Camera movement speed
    private final int speed = 5;
    private int x,y;
    private Handler handler;
    
    public Camera(Handler handler, int x, int y) {
        
        this.handler = handler;
        this.x = x;
        this.y = y;
    }
    
    public void tick() {
        
        //Allow camera to go 4 tiles offscreen in all directions
        //so that interfaces don't hide tiles on the edges of map
        if (handler.getKeyManager().up) {
            if (!(y < -(Tile.TILEHEIGHT * 4)))
                y -= speed;
        }
        if (handler.getKeyManager().down) {
            if (!(y+handler.getHeight() > (Tile.TILEHEIGHT * (handler.getMap().getHeight() + 4))))
                y += speed;
        }
        if (handler.getKeyManager().left) {
            if (!(x < -(Tile.TILEWIDTH*4)))
                x -= speed;
        }
        if (handler.getKeyManager().right) {
            if (!(x+handler.getWidth() > (Tile.TILEWIDTH * (handler.getMap().getWidth()+ 4))))
                x += speed;
        }
        //center the game camera on this object
        handler.getGameCamera().centerOnCamera(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
}
