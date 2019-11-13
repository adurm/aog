package aog2.states;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author adam
 * class MenuState ticks/renders everything to do with the menu (not yet relevant)
 */
public class MainMenuState extends State {

    public MainMenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        Text.drawString(g, "Play", 400, 300, true, Color.white, Assets.font20);
    }
}
