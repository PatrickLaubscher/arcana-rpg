package rpg.services;

import rpg.characters.Enemy;
import rpg.characters.Hero;

import java.util.Scanner;

public interface CombatManager {

    // score enemy count


    // input player choice during combat turn throw exception


    // combat turn
    public void combatTurn(Hero hero, Enemy enemy, Scanner sc);

    // if the enemy is still alive it attacks back
    public void attackReturn(Hero hero, Enemy enemy, Scanner sc);

    // create new enemy randomly
    public Enemy generateNewEnemy();

    // save score in file scores.txt


}
