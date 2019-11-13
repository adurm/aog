package aog2.game.graphics;

import aog2.game.helpers.SpriteSheet;
import aog2.game.helpers.ImageLoader;
import aog2.game.helpers.FontLoader;
import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 *
 * @author adam
 *
 * Class Assets initializes all images and fonts used
 */
public class Assets {
    
    //WIDTH + HEIGHT
    private static final int WIDTH = 40, HEIGHT = 40;

    //FONTS
    public static Font font9, font11, font16, font20;
    //INTERFACES
    public static BufferedImage tileInfo2, globe2, globehover2, manabar2, unitinfo2;
    public static BufferedImage progresspanel2, white, red, blue, green;
    public static BufferedImage endturn, endturnhover, clock2, unithoverpanel;
    //ICONS
    public static BufferedImage redmove, yellowmove, greenmove;
    public static BufferedImage blueflag, redflag;
    public static BufferedImage warriorIcon, wizardIcon, archerIcon, helperIcon;
    //UNITS
    public static BufferedImage archer, knight, wizard, peasant, chief;    
    //TILES
    public static BufferedImage[] roadtiles, rivertiles, mudtiles, seatiles, rocktiles;
    public static BufferedImage bordertile, opentile, treetile;
    public static BufferedImage toweropentile, towerroadtile, houseopentile, houseroadtile;
    public static BufferedImage emptyportalopentile, emptyportalroadtile;
    public static BufferedImage blueportalopentile, blueportalroadtile;
    public static BufferedImage redportalopentile, redportalroadtile;
    
    //SPRITESHEETS
    private static SpriteSheet interfaces = new SpriteSheet(ImageLoader.loadImage("/textures/interfaces.png"));
    private static SpriteSheet units = new SpriteSheet(ImageLoader.loadImage("/textures/units.png"));
    private static SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));

    public static void init() {

        initFonts();
        initTiles();
        initInterfaces();
        initIcons();
        initUnits();
    }

    private static void initFonts() {
        
        font20 = FontLoader.loadFont("res/fonts/font.ttf", 20);
        font16 = FontLoader.loadFont("res/fonts/font.ttf", 16);
        font11 = FontLoader.loadFont("res/fonts/font.ttf", 11);
        font9 = FontLoader.loadFont("res/fonts/font.ttf", 9);
    }

    private static void initTiles() {
        
        bordertile = tiles.crop(320, 0, WIDTH, HEIGHT);
        opentile = tiles.crop(360, 0, WIDTH, HEIGHT);
        treetile = tiles.crop(320, 80, WIDTH, HEIGHT);
        
        toweropentile = tiles.crop(400, 0, WIDTH, HEIGHT);
        towerroadtile = tiles.crop(440, 0, WIDTH, HEIGHT);
        houseopentile = tiles.crop(400, 40, WIDTH, HEIGHT);
        houseroadtile = tiles.crop(440, 40, WIDTH, HEIGHT);
      
        emptyportalopentile = tiles.crop(320, 159, WIDTH, HEIGHT);
        emptyportalroadtile = tiles.crop(360, 159, WIDTH, HEIGHT);
        redportalopentile = tiles.crop(320, 219, WIDTH, HEIGHT);
        redportalroadtile = tiles.crop(360, 219, WIDTH, HEIGHT);
        blueportalopentile = tiles.crop(320, 279, WIDTH, HEIGHT);
        blueportalroadtile = tiles.crop(360, 279, WIDTH, HEIGHT);

        rocktiles = new BufferedImage[2];
        rocktiles[0] = tiles.crop(320, 40, WIDTH, HEIGHT);
        rocktiles[1] = tiles.crop(360, 40, WIDTH, HEIGHT);
        
        rivertiles = new BufferedImage[16];
        rivertiles[0] = tiles.crop(0, 0, WIDTH, HEIGHT);
        rivertiles[1] = tiles.crop(40, 0, WIDTH, HEIGHT);
        rivertiles[2] = tiles.crop(80, 0, WIDTH, HEIGHT);
        rivertiles[3] = tiles.crop(120, 0, WIDTH, HEIGHT);
        rivertiles[4] = tiles.crop(0, 40, WIDTH, HEIGHT);
        rivertiles[5] = tiles.crop(40, 40, WIDTH, HEIGHT);
        rivertiles[6] = tiles.crop(80, 40, WIDTH, HEIGHT);
        rivertiles[7] = tiles.crop(120, 40, WIDTH, HEIGHT);
        rivertiles[8] = tiles.crop(0, 80, WIDTH, HEIGHT);
        rivertiles[9] = tiles.crop(40, 80, WIDTH, HEIGHT);
        rivertiles[10] = tiles.crop(80, 80, WIDTH, HEIGHT);
        rivertiles[11] = tiles.crop(120, 80, WIDTH, HEIGHT);
        rivertiles[12] = tiles.crop(0, 120, WIDTH, HEIGHT);
        rivertiles[13] = tiles.crop(40, 120, WIDTH, HEIGHT);
        rivertiles[14] = tiles.crop(80, 120, WIDTH, HEIGHT);
        rivertiles[15] = tiles.crop(120, 120, WIDTH, HEIGHT);
        
        roadtiles = new BufferedImage[16];
        roadtiles[0] = tiles.crop(0, 160, WIDTH, HEIGHT);
        roadtiles[1] = tiles.crop(40, 160, WIDTH, HEIGHT);
        roadtiles[2] = tiles.crop(80, 160, WIDTH, HEIGHT);
        roadtiles[3] = tiles.crop(120, 160, WIDTH, HEIGHT);
        roadtiles[4] = tiles.crop(0, 200, WIDTH, HEIGHT);
        roadtiles[5] = tiles.crop(40, 200, WIDTH, HEIGHT);
        roadtiles[6] = tiles.crop(80, 200, WIDTH, HEIGHT);
        roadtiles[7] = tiles.crop(120, 200, WIDTH, HEIGHT);
        roadtiles[8] = tiles.crop(0, 240, WIDTH, HEIGHT);
        roadtiles[9] = tiles.crop(40, 240, WIDTH, HEIGHT);
        roadtiles[10] = tiles.crop(80, 240, WIDTH, HEIGHT);
        roadtiles[11] = tiles.crop(120, 240, WIDTH, HEIGHT);
        roadtiles[12] = tiles.crop(0, 280, WIDTH, HEIGHT);
        roadtiles[13] = tiles.crop(40, 280, WIDTH, HEIGHT);
        roadtiles[14] = tiles.crop(80, 280, WIDTH, HEIGHT);
        roadtiles[15] = tiles.crop(120, 280, WIDTH, HEIGHT);
        
        mudtiles = new BufferedImage[16];
        mudtiles[0] = tiles.crop(160, 0, WIDTH, HEIGHT);
        mudtiles[1] = tiles.crop(200, 0, WIDTH, HEIGHT);
        mudtiles[2] = tiles.crop(240, 0, WIDTH, HEIGHT);
        mudtiles[3] = tiles.crop(280, 0, WIDTH, HEIGHT);
        mudtiles[4] = tiles.crop(160, 40, WIDTH, HEIGHT);
        mudtiles[5] = tiles.crop(200, 40, WIDTH, HEIGHT);
        mudtiles[6] = tiles.crop(240, 40, WIDTH, HEIGHT);
        mudtiles[7] = tiles.crop(280, 40, WIDTH, HEIGHT);
        mudtiles[8] = tiles.crop(160, 80, WIDTH, HEIGHT);
        mudtiles[9] = tiles.crop(200, 80, WIDTH, HEIGHT);
        mudtiles[10] = tiles.crop(240, 80, WIDTH, HEIGHT);
        mudtiles[11] = tiles.crop(280, 80, WIDTH, HEIGHT);
        mudtiles[12] = tiles.crop(160, 120, WIDTH, HEIGHT);
        mudtiles[13] = tiles.crop(200, 120, WIDTH, HEIGHT);
        mudtiles[14] = tiles.crop(240, 120, WIDTH, HEIGHT);
        mudtiles[15] = tiles.crop(280, 120, WIDTH, HEIGHT);
        
        seatiles = new BufferedImage[16];
        seatiles[0] = tiles.crop(160, 160, WIDTH, HEIGHT);
        seatiles[1] = tiles.crop(200, 160, WIDTH, HEIGHT);
        seatiles[2] = tiles.crop(240, 160, WIDTH, HEIGHT);
        seatiles[3] = tiles.crop(280, 160, WIDTH, HEIGHT);
        seatiles[4] = tiles.crop(160, 200, WIDTH, HEIGHT);
        seatiles[5] = tiles.crop(200, 200, WIDTH, HEIGHT);
        seatiles[6] = tiles.crop(240, 200, WIDTH, HEIGHT);
        seatiles[7] = tiles.crop(280, 200, WIDTH, HEIGHT);
        seatiles[8] = tiles.crop(160, 240, WIDTH, HEIGHT);
        seatiles[9] = tiles.crop(200, 240, WIDTH, HEIGHT);
        seatiles[10] = tiles.crop(240, 240, WIDTH, HEIGHT);
        seatiles[11] = tiles.crop(280, 240, WIDTH, HEIGHT);
        seatiles[12] = tiles.crop(160, 280, WIDTH, HEIGHT);
        seatiles[13] = tiles.crop(200, 280, WIDTH, HEIGHT);
        seatiles[14] = tiles.crop(240, 280, WIDTH, HEIGHT);
        seatiles[15] = tiles.crop(280, 280, WIDTH, HEIGHT);
    }

    private static void initInterfaces() {
        
        unitinfo2 = interfaces.crop(0, 0, 300, 64);
        tileInfo2 = interfaces.crop(0, 64, 300, 64);
        progresspanel2 = interfaces.crop(0, 129, 100, 120);
        manabar2 = interfaces.crop(103, 213, 70, 35);
        unithoverpanel = interfaces.crop(282, 135, 120, 61);
        clock2 = interfaces.crop(303, 4, 100, 100);
        endturn = interfaces.crop(185, 130, 90, 20);
        endturnhover = interfaces.crop(185, 151, 90, 20);
        globe2 = interfaces.crop(190, 173, 40, 40);
        globehover2 = interfaces.crop(236, 175, 38, 37);
        white = interfaces.crop(101, 129, 40, 40);
        blue = interfaces.crop(143, 129, 40, 40);
        red = interfaces.crop(101, 171, 40, 40);
        green = interfaces.crop(143, 171, 40, 40);
    }

    private static void initIcons() {
        
        redmove = interfaces.crop(1, 251, 25, 25);
        yellowmove = interfaces.crop(28, 251, 25, 25);
        greenmove = interfaces.crop(56, 251, 25, 25);
        blueflag = interfaces.crop(83, 251, 25, 25);
        redflag = interfaces.crop(113, 251, 25, 25);
        wizardIcon = interfaces.crop(141, 251, 25, 25);
        archerIcon = interfaces.crop(168, 251, 25, 25);
        helperIcon = interfaces.crop(195, 251, 25, 25);
        warriorIcon = interfaces.crop(225, 252, 22, 22);
    }

    private static void initUnits() {
        
        archer = units.crop(190, 1, 198, 331);
        knight = units.crop(389, 1, 150, 331);
        wizard = units.crop(0, 1, 190, 355);
        peasant = units.crop(567, 1, 160, 331);
        chief = units.crop(567, 1, 160, 331);
    }

}
