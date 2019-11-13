package aog2.states;

import aog2.game.units.units.Chief;
import aog2.game.capturing.StructureFlags;
import aog2.game.attacks.AttackRange;
import aog2.game.turns.*;
import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.camera.Camera;
import aog2.game.helpers.Text;
import aog2.game.graphics.TileHover;
import aog2.game.interfaces.*;
import aog2.game.maps.Map;
import aog2.game.movement.*;
import aog2.game.players.Player;
import aog2.game.portal.*;
import aog2.game.units.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author adam class GameState initializes the map takes in all other relevant
 * classes, ticks and renders them
 */
public class GameState extends State {

    private Map map;
    private Camera camera;
    private TileHover tileHover;
    private InterfaceManager interfaceManager;
    private UnitManager unitManager;
    private MovementManager movementManager;
    private PortalManager portalManager;
    private TurnManager turnManager;
    private Player p1, p2;
    private Interface menu;
    private StructureFlags structureFlags;
    private WinConditions winConditions;

    public GameState(Handler handler, Player[] players) {

        super(handler);
        map = new Map(handler, "res/maps/map1.txt");
        handler.setMap(map);

        camera = new Camera(handler, 0, 0);
        tileHover = new TileHover(handler);
        interfaceManager = new InterfaceManager(handler);
        unitManager = new UnitManager(handler);
        movementManager = new MovementManager(handler);
        portalManager = new PortalManager(handler);
        turnManager = new TurnManager(handler);
        structureFlags = new StructureFlags(handler);
        winConditions = new WinConditions(handler);

        //Interfaces
        interfaceManager.addInterface(new TileInfo(handler));
        interfaceManager.addInterface(new ManaBar(handler));
        interfaceManager.addInterface(new EndTurn(handler));
        //interfaceManager.addInterface(new Favour(handler));
        interfaceManager.addInterface(new ProgressPanel(handler));
        interfaceManager.addInterface(new Minimap(handler));
        interfaceManager.addInterface(new MinimapIcon(handler));
        interfaceManager.addInterface(new UnitHoverPanel(handler));
        interfaceManager.addInterface(new TurnIndicator(handler));
        menu = new Menu(handler);
        interfaceManager.addInterface(menu);

        //Movements
        movementManager.addMovement(new MoveRange(handler));
        movementManager.addMovement(new AttackRange(handler));

        //Portal
        portalManager.addPortal(new PortalInterface(handler));
        portalManager.addPortal(new PortalSummoning(handler));

        //Players
        p1 = players[0];
        p2 = players[1];

        initChiefs();
    }

    @Override
    public void tick() {

        if (Interface.menuOpen) {
            menu.tick();
            return;
        }

        if (TurnManager.switchingTurn) {
            turnManager.tick();
            return;
        }

        turnManager.tick();
        camera.tick();
        map.tick();
        movementManager.tick();
        winConditions.tick();
        tileHover.tick();
        portalManager.tick();  
        interfaceManager.tick();
    }

    @Override
    public void render(Graphics g) {

        if (Interface.menuOpen) {
            menu.render(g);
            return;
        } else if (TurnManager.switchingTurn) {
            turnManager.showTurnStart(g);
            return;
        } else if (!"".equals(WinConditions.winner)){
            winConditions.render(g);
            return;
        }

        //Black Background
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Text.drawString(g, "Developed by Adam",
                handler.getWidth() - 120, handler.getHeight() - 15,
                true, Color.white, Assets.font9);
        
        
        map.render(g);
        structureFlags.render(g);
        unitManager.render(g);
        movementManager.render(g);
        tileHover.render(g);
        turnManager.render(g);
        portalManager.render(g);
        interfaceManager.render(g);
    }

    private void initChiefs() {
        
        Unit chief1 = new Chief(handler, p1);
        handler.getMap().addUnit(chief1, 1, 4);
        handler.getMap().getTileAt(1, 4).addUnit(chief1);

        Unit chief2 = new Chief(handler, p2);
        handler.getMap().addUnit(chief2, 20, 17);
        handler.getMap().getTileAt(20, 17).addUnit(chief2);
    }

}
