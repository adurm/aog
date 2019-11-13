package aog2.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 
 * @author adam
 * 
 * Class MouseManager handles mouse input from the user
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean[] buttons, justPressed, cantPress;

    public MouseManager() {

        buttons = new boolean[256];
        justPressed = new boolean[buttons.length];
        cantPress = new boolean[buttons.length];
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void tick() {

        for (int i = 0; i < buttons.length; i++) {

            if (cantPress[i] && !buttons[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && buttons[i]) {
                justPressed[i] = true;
            }
        }
    }

    public boolean buttonJustPressed(int mouseButton) {

        if (mouseButton < 0 || mouseButton >= buttons.length) {
            return false;
        }
        return justPressed[mouseButton];
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() < 0
                || e.getButton() > buttons.length) {
            return;
        }
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getButton() < 0
                || e.getButton() > buttons.length) {
            return;
        }
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

}
