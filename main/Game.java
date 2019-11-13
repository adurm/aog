package aog2.main;

import aog2.states.MainMenuState;
import aog2.states.GameState;
import aog2.states.State;
import aog2.game.graphics.Display;
import aog2.game.camera.GameCamera;
import aog2.game.helpers.Handler;
import aog2.game.graphics.*;
import aog2.game.input.*;
import aog2.game.players.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author adam
 *
 * Class Game controls the game loop which ticks and renders all elements of the
 * game
 */
public class Game extends Canvas implements Runnable {

    //Game Display
    private Display display;
    private String title;
    private final int width, height;

    //Game Graphics
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;

    //Game States
    private State gameState;
    private State menuState;

    //Input Managers
    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    //Others
    private GameCamera gameCamera;
    private Handler handler;
    private Player[] players;

    public Game(String title, int width, int height) {

        this.width = width;
        this.height = height;
        this.title = title;
        
        handler = new Handler(this);
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
        players = new Player[2];
        players[0] = new Player(handler, new Color(30, 144, 255));
        players[1] = new Player(handler, Color.red);
    }

    private void init() {

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        gameCamera = new GameCamera(handler, 0, 0);
        gameState = new GameState(handler, players);
        menuState = new MainMenuState(handler);
        
        //Initial State of Game
        State.setState(gameState);
    }

    private void tick() {

        keyManager.tick();
        mouseManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {

        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //Draw Game State
        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    //Game loop
    @Override
    public void run() {

        init();

        int fps = 40;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                display.getFrame().setTitle("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {

        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public Player getPlayer(int id) {
        return players[id];
    }

    public Player[] getPlayers() {
        return players;
    }

    //Main method
    public static void main(String[] args) {

        Game game = new Game("Game", 1200, 800);
        game.start();
    }

}
