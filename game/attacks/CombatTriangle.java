package aog2.game.attacks;

import aog2.game.helpers.Handler;
import aog2.game.units.Type;

/**
 *
 * @author adam
 */
public class CombatTriangle {

    private Handler handler;

    public CombatTriangle(Handler handler) {
        this.handler = handler;
    }

    public double calcAttackDamagePercentage(Type t1, Type t2) {

        if (null != t1) {
            switch (t1) {
                case Warrior:
                    if (null != t2) {
                        switch (t2) {
                            case Archer:
                                return 0.9;
                            case Warrior:
                                return 0.5;
                            case Wizard:
                                return 0.25;
                            case Helper:
                                return 0.9;
                            default:
                                break;
                        }
                    }
                    break;
                case Archer:
                    if (null != t2) {
                        switch (t2) {
                            case Archer:
                                return 0.5;
                            case Warrior:
                                return 0.25;
                            case Wizard:
                                return 0.9;
                            case Helper:
                                return 0.5;
                            default:
                                break;
                        }
                    }
                    break;
                case Wizard:
                    if (null != t2) {
                        switch (t2) {
                            case Archer:
                                return 0.25;
                            case Warrior:
                                return 0.9;
                            case Wizard:
                                return 0.25;
                            case Helper:
                                return 0.5;
                            default:
                                break;
                        }
                    }
                    break;
                case Helper:
                    return 0.5;
                default:
                    break;
            }
        }

        return 0;
    }

    //archers + wizards dont retaliate or get retaliated to
    //all other cases is warrior vs helper which is 0.5
    public double calcRetalDamagePercentage(Type t1, Type t2) {

        if (t1 == Type.Wizard
                || t1 == Type.Archer
                || t2 == Type.Wizard
                || t2 == Type.Archer) {
            return 0;
        } else {
            return 0.5;
        }

    }
}
