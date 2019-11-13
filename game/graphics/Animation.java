
package aog2.game.graphics;

import java.awt.image.BufferedImage;

/**
 * 
 * @author adam
 * 
 * Class Animation handles cycling through a BufferedImage array
 * which produces an animation
 */
public class Animation {
    
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;
    
    public Animation(int speed, BufferedImage[] frames){
        
        this.speed= speed;
        this.frames= frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void tick() {
        
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed) { //when speed is reached
            index++; //change image
            timer = 0; //reset time
            if (index >= frames.length) //when array end is reached
                index = 0; //start from first
        }
    }
    
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
