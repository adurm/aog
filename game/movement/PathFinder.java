
package aog2.game.movement;

import aog2.game.helpers.Handler;
import aog2.game.tiles.Tile;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 *
 * @author adam
 */
public class PathFinder extends Movement{

    public PathFinder(Handler handler) {
        super(handler);
    }
    
    public ArrayList<Tile> path(Tile startTile, Tile endTile){
        
        PriorityQueue<Tile> open = new PriorityQueue<>();
        ArrayList<Tile> closed = new ArrayList<>();

        open.add(startTile);
        
        while (!open.isEmpty()){
           
            for (Tile t : open){
                
            }
        }
        return null;
        
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
    
    
    
}
