
package aog2.game.helpers;

import aog2.main.Game;
import aog2.game.camera.GameCamera;
import aog2.game.input.KeyManager;
import aog2.game.input.MouseManager;
import aog2.game.maps.Map;
import aog2.game.turns.TurnManager;
import aog2.game.players.Player;
import aog2.game.helpers.Position;

/**
 * 
 * @author adam
 * 
 * Class Handler allows for classes to reference and retrieve data from one another
 */
public class Handler {
    
    private Game game;
    private Map map;
    private Position position;
    
    public Handler(Game game) {
        this.game = game;
        position = new Position(this);
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Position getPosition() {
        return position;
    }
    
    public Player getPlayer(int id){
        return game.getPlayer(id);
    }
    
    public Player[] getPlayers(){
        return game.getPlayers();
    }
    
    public Player getPlayersTurn(){
        
        for (int i = 0; i < TurnManager.turn.length; i++){
            if (TurnManager.turn[i] == true){
                return game.getPlayer(i);
            }
        }
        return null;
    }
    
}
