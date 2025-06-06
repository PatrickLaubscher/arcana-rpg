package rpg.services.impl;

import rpg.characters.*;
import rpg.services.CombatManager;

import java.util.Scanner;

public class CombatManagerImpl implements CombatManager {

    public void combatTurn(Hero hero, Enemy enemy, Scanner sc) {

            System.out.println(
                    "Choisissez une action :"
            );
            System.out.println(
                    "1 - Attaque"
            );
            System.out.println(
                    "2 - Pouvoir magique"
            );
            System.out.println(
                    "3 - Utiliser une potion"
            );
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println(hero.getName() + " attaque " + enemy.getName());
                    int diceResult = DiceRollerImpl.getInstance().throwDice();
                    if (diceResult == 6) {
                        System.out.println("Critique ! dommages doublés !");
                    } else {
                        System.out.println("Vous avez obtenu un : " + diceResult);
                    }
                    int heroDmg = hero.attack(enemy, diceResult);
                    System.out.println("Vous infligez : " + heroDmg);
                    enemy.takeDamages(heroDmg);
                }
                case 2 -> System.out.println("Le héro utilise son pouvoir spécial");
                case 3 -> System.out.println("Le héro boit une potion");
                default -> throw new IllegalStateException("Commande non valable: " + choice);
            }

            // If the enemy is still alive, it strikes back
            this.attackReturn(hero, enemy, sc);
    }


    public void attackReturn(Hero hero, Enemy enemy, Scanner sc) {

            if(enemy.isAlive()){
                System.out.println(enemy.getName() + " attaque " + hero.getName());
                int diceResult = DiceRollerImpl.getInstance().throwDice();
                if(diceResult == 6) {
                    System.out.println("Attaque critique ! dommages doublés !");
                }
                int enemyDmg = enemy.attack(hero, diceResult);
                System.out.println(enemy.getName() + "inflige : " + enemyDmg);
                hero.takeDamages(enemyDmg);
            } else {
                System.out.println("Bravo ! " + hero.getName() + " a tué " + enemy.getName());
                hero.setKillerCount(hero.getKillerCount() + 1);
            }
    }


    public Enemy generateNewEnemy() {
        int rand = (int)(Math.random()*100);
        if(rand < 51) {
            return new Goblin();
        } else if(rand < 91) {
            return new Troll();
        } else {
            return new Dragon();
        }
    }

}
