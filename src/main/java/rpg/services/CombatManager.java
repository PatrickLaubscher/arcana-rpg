package rpg.services;

import rpg.characters.Enemy;
import rpg.characters.Hero;

import java.util.Scanner;

public interface CombatManager {

    // score enemy count


    // input player choice during combat turn throw exception


    // combat turn
    void combatTurn(Hero hero, Enemy enemy, Scanner sc);

    // if the enemy is still alive it attacks back
    void attackReturn(Hero hero, Enemy enemy, Scanner sc);

    // create new enemy randomly on random base 100
    Enemy generateNewEnemy();

    // display remaining health point
    void remainingHealthPoint(Hero hero);

    // save score in file scores.txt


}
