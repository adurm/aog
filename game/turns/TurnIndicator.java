
package aog2.game.turns;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import aog2.game.interfaces.Interface;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author adam
 * class TurnIndicator displays at the top right of the screen
 */
public class TurnIndicator extends Interface{

    private int width = 140; 
    private int height = 80;
    
    public TurnIndicator(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(handler.getPlayersTurn().getColor());
        g.fillRect(handler.getWidth()-width-10,
                10, width, height);
        
        String s;
        if (handler.getPlayersTurn() == handler.getPlayer(0)){
            s = "BLUES TURN";
        } else {
            s = "REDS TURN";
        }
        Text.drawString(g, s, handler.getWidth()-(width/2)-10, 5+(height/2),
                true, Color.white, Assets.font20);        
    }
    
}
