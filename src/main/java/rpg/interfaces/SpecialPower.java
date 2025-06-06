package rpg.interfaces;

import rpg.characters.Enemy;
import rpg.services.exceptions.CheckManaException;

public interface SpecialPower {

    // check mana balance
    void checkManaBalance(int powerMana) throws CheckManaException;

    // use special power to deal extra damage but use mana
    int useSpecialPower (Enemy target, int diceResult);
}
