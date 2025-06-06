package rpg.services.impl;

import rpg.characters.Enemy;
import rpg.characters.Hero;
import rpg.services.CombatManager;

import java.util.Scanner;

public class CombatManagerImpl implements CombatManager {


    public void combatTurn(Hero hero, Enemy enemy) {

        try(Scanner sc = new Scanner(System.in)) {

            while(true) {
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
                    case 1 -> enemy.takeDamages(hero.attack(enemy));
                    case 2 -> System.out.println("Le héro utilise son pouvoir spécial");
                    case 3 -> System.out.println("Le héro boit une potion");
                    default -> throw new IllegalStateException("Commande non valable: " + choice);
                }

                if(enemy.isAlive()){
                    hero.takeDamages(hero.attack(hero));
                } else {
                    hero.setKillerCount(hero.getKillerCount() + 1);
                }
            }
        }



    }
 }
