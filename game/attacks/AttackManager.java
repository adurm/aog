
package aog2.game.attacks;

import aog2.game.helpers.Handler;
import aog2.game.graphics.Assets;
import aog2.game.helpers.Text;
import aog2.game.movement.Movement;
import aog2.game.tiles.Tile;
import aog2.game.units.Type;
import aog2.game.units.Unit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 *
 * @author adam
 */
public class AttackManager extends Movement {

    private CombatTriangle combatTriangle;
    private int atkDmg, retalDmg;
    private Unit attacker, retaliater;
    private Tile attackerTile, retaliaterTile;
    private int attackerTilex, attackerTiley;
    private int retaliaterTilex, retaliaterTiley;

    public AttackManager(Handler handler) {
        super(handler);
        combatTriangle = new CombatTriangle(handler);
    }

    
    public void calculateAttackDamage(Tile attackerTile, Unit attacker, int attackerTilex, int attackerTiley,
            Tile retaliaterTile, Unit retaliater, int retaliaterTilex, int retaliaterTiley) {

        this.attackerTile = attackerTile;
        this.retaliaterTile = retaliaterTile;
        this.attacker = attacker;
        this.retaliater = retaliater;
        this.attackerTilex = attackerTilex;
        this.attackerTiley = attackerTiley;
        this.retaliaterTilex = retaliaterTilex;
        this.retaliaterTiley = retaliaterTiley;

        //damage = attacker hp * type modifier * terrain
        
        int attackerHP = attacker.getCurrentHP();
        double modifier = combatTriangle.calcAttackDamagePercentage(attacker.getType(), retaliater.getType());
        int rawDamage = (int) (attackerHP * modifier);
        double defence = 1 - (retaliaterTile.getDefence() / 100.0);
        atkDmg = (int) (rawDamage * defence);

        //minimum attack is 1
        if (atkDmg < 1) {
            atkDmg = 1;
        }
        //update unit health
        retaliater.setCurrentHP(retaliater.getCurrentHP() - atkDmg);
        //if you killed the unit, you level up
        if (retaliater.getCurrentHP() <= 0) {
            attacker.setCurrentHP(attacker.getCurrentHP() + 1);
            attacker.setMaxHP(attacker.getMaxHP() + 1);
        } else { //else the attacked will retaliate
            calculateRetaliationDamage(attackerTile, attacker, retaliaterTile, retaliater);
        }

        attacking = true;
    }

    private void calculateRetaliationDamage(Tile attackerTile, Unit attacker,
            Tile retaliaterTile, Unit retaliater) {

        //work out damage retaliated
        int retaliaterHP = retaliater.getCurrentHP();
        double modifier = combatTriangle.calcRetalDamagePercentage(attacker.getType(), retaliater.getType());
        int rawDamage = (int) (retaliaterHP * modifier);
        double defence = 1 - (attackerTile.getDefence() / 100.0);
        retalDmg = (int) (rawDamage * defence);

        attacker.setCurrentHP(attacker.getCurrentHP() - retalDmg);
        
        //if you kill the unit, level up
        if (attacker.getCurrentHP() <= 0) {
            retaliater.setCurrentHP(retaliater.getCurrentHP() + 1);
            retaliater.setMaxHP(retaliater.getMaxHP() + 1);
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

        if (attacking) {
            g.setColor(Color.red);
            g.fillOval(handler.getPosition().fixToLocWithXCoord(retaliaterTilex) + 5,
                    handler.getPosition().fixToLocWithYCoord(retaliaterTiley) + 5, 25, 25);
            Text.drawString(g, "" + atkDmg, handler.getPosition().fixToLocWithXCoord(retaliaterTilex) + 20,
                    handler.getPosition().fixToLocWithYCoord(retaliaterTiley) + 20,
                    true, Color.white, Assets.font16);
            
            Timer timer = new Timer(800, (ActionEvent e) -> {
                attacking = false;
                
            });
            timer.setRepeats(false);
            timer.start();

            Timer timer2 = new Timer(800, (ActionEvent e) -> {
                if (retaliater.getCurrentHP() <= 0) {
                    handler.getMap().getTileAt(retaliaterTilex, retaliaterTiley).removeUnit();
                    handler.getMap().removeUnit(retaliaterTilex, retaliaterTiley);
                } else {
                    retaliating = true;
                }
            });
            timer2.setRepeats(false);
            timer2.start();
            
        }
        if (retaliating && retalDmg != 0 && !(retaliater.getCurrentHP() <= 0)) {
            g.setColor(Color.red);
            g.fillOval(handler.getPosition().fixToLocWithXCoord(attackerTilex) + 5,
                    handler.getPosition().fixToLocWithYCoord(attackerTiley) + 5, 25, 25);
            Text.drawString(g, "" + retalDmg, handler.getPosition().fixToLocWithXCoord(attackerTilex) + 20,
                    handler.getPosition().fixToLocWithYCoord(attackerTiley) + 20,
                    true, Color.white, Assets.font16);
            
            
            Timer timer = new Timer(800, (ActionEvent e) -> {
                retaliating = false;
                if (attacker.getCurrentHP() <= 0) {
                    handler.getMap().getTileAt(attackerTilex, attackerTiley).removeUnit();
                    handler.getMap().removeUnit(attackerTilex, attackerTiley);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
