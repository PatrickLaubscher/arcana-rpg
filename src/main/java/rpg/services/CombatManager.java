package rpg.services;

import rpg.characters.Enemy;
import rpg.characters.Hero;

public interface CombatManager {

    // score enemy count


    // input player choice during combat turn throw exception


    // combat turn
    public void combatTurn(Hero hero, Enemy enemy);

    // create new enemy randomly

    // save score in file scores.txt

}
