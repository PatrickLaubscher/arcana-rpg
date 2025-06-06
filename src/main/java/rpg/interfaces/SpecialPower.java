package rpg.interfaces;

import rpg.characters.Enemy;

public interface SpecialPower {
    // use special power to deal extra damage but use mana
    int useSpecialPower(Enemy target, int diceResult);
}
