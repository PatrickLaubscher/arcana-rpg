package rpg.services.impl;

import rpg.characters.Enemy;
import rpg.characters.Hero;
import rpg.services.CombatManager;

import java.util.Scanner;

public class CombatManagerImpl implements CombatManager {

    public void combatTurn(Hero hero, Enemy enemy, Scanner sc) {

            System.out.println(
                    "Choisissez une action pour votre héro :"
            );
            System.out.println(
                    "1 - Votre héro attaque"
            );
            System.out.println(
                    "2 - Votre héro utilise son pouvoir spécial"
            );
            System.out.println(
                    "3 - Votre héro utilise une potion"
            );
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println(hero.getName() + " attaque " + enemy.getName());
                    System.out.println("Vous jetez le dé");
                    int diceResult = DiceRollerImpl.getInstance().throwDice();
                    if (diceResult == 6) {
                        System.out.println("Critique ! vos dégâts sont doublés !");
                    } else {
                        System.out.println("Résultat : " + diceResult);
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
                    System.out.println("Attaque critique ! dégâts doublés !");
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
        return new Enemy();
    }

}
