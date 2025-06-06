package rpg.services;

import rpg.characters.Enemy;
import rpg.characters.Hero;

import java.util.Scanner;

public interface CombatManager {

    // score enemy count

    // combat turn
    public void combatTurn(Hero hero, Enemy enemy); {

        try(Scanner sc = new Scanner(System.in)) {
            
            switch (choice) {
                case 1 -> hero.attack(enemy);
                default ->
            }

        }


    }

    // create new enemy randomly

    // save score in file scores.txt

}
