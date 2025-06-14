package rpg.services.impl;

import rpg.characters.*;
import rpg.services.CombatManager;
import rpg.services.exceptions.CheckManaException;
import rpg.services.exceptions.CheckPotionException;

import java.util.Scanner;

public class CombatManagerImpl implements CombatManager {

    private int enemyCount = 1;

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }



    // display begin phase of combat turn
    public void beginCombatTurn(Hero hero, Enemy enemy) {

        // Display enemi number
        System.out.println("-------------------------------------------------------");
        System.out.println("| Ennemi " + enemyCount);
        // Display enemy health point
        System.out.println("Point de vie restant au " + enemy.getName() + " : " + enemy.getHealth());
        System.out.println("-------------------------------------------------------");
        System.out.println();
        // Display hero info
        System.out.println("-------------------------------------------------------");
        System.out.println("| Il vous reste :                                      ");
        System.out.println("|---------------- " + hero.getHealth() + " pts de vie  ");
        System.out.println("|---------------- " + hero.getMana() + " pts de mana   ");
        System.out.println("|---------------- " + hero.getPotionNb() + " potion    ");
        System.out.println("-------------------------------------------------------");
        System.out.println();

    }

    // input player choice action
    public int inputPlayerChoice(Scanner sc) {

        System.out.println("Choisissez une action :");
        System.out.println("1 - Attaquer");
        System.out.println("2 - Utiliser votre pouvoir magique");
        System.out.println("3 - Utiliser une potion");
        return sc.nextInt();

    }

    // display & manage player attacking enemy
    public void playerAttackEnemy(Hero hero, Enemy enemy, Scanner sc) {

        System.out.println();
        System.out.println(hero.getName() + " attaque " + enemy.getName());
        int diceResult = DiceRollerImpl.getInstance().throwDice();
        if (diceResult == 6) {
            System.out.println("Critique ! dégâts doublés !");
        } else {
            System.out.println("Vous avez obtenu un : " + diceResult);
        }
        int heroDmg = hero.attack(enemy, diceResult);
        System.out.println("Vous infligez : " + heroDmg + " dégâts");
        enemy.takeDamages(heroDmg);
        // If the enemy is still alive, it strikes back
        if (!enemy.isAlive()) {
            System.out.println("Bravo ! " + hero.getName() + " a tué " + enemy.getName());
            // input killer count
            hero.setKillerCount(hero.getKillerCount() + 1);
            this.enemyCount++;
        } else {
            this.attackReturn(hero, enemy, sc);
        }
    }

    // display & manage player using power on enemy
    public void playerUsePowerOnEnemy(Hero hero, Enemy enemy, Scanner sc) {

        try {
            hero.checkManaBalance(hero.getPowerMana());
            System.out.println("Vous utilisez votre pouvoir magique");
            int diceResult = DiceRollerImpl.getInstance().throwDice();
            if (diceResult == 6) {
                System.out.println("Critique ! dégâts doublés !");
            } else {
                System.out.println("Vous avez obtenu un : " + diceResult);
            }
            int heroDmg = hero.useSpecialPower(enemy, diceResult);
            System.out.println("Vous infligez : " + heroDmg + " dégâts");
            System.out.println("Il vous reste " + hero.getMana() + " mana");
            enemy.takeDamages(heroDmg);
            // If the enemy is still alive, it strikes back
            if (!enemy.isAlive()) {
                System.out.println("Bravo ! " + hero.getName() + " a tué " + enemy.getName());
                // input killer count
                hero.setKillerCount(hero.getKillerCount() + 1);
                this.enemyCount++;
            } else {
                this.attackReturn(hero, enemy, sc);
            }
        } catch (CheckManaException e) {
            System.out.println(e.getMessage());
        }

    }

    // display & manage player using a health potion
    public void playerUsePotion(Hero hero) {

        try {
            hero.checkRemainingPotion();
            System.out.println(hero.getName() + " boit une potion");
            hero.useHealthPotion();
            System.out.println("Votre vitalité est restaurée");
        } catch (CheckPotionException e) {
            System.out.println(e.getMessage());
        }

    }


    // display combat turn and player action choice
    public void combatTurn(Hero hero, Enemy enemy, Scanner sc) {

        // begin combat turn
        this.beginCombatTurn(hero, enemy);
        // input player choice
        int playerChoice = this.inputPlayerChoice(sc);
        // switch on player choice
        switch (playerChoice) {
            case 1 -> this.playerAttackEnemy(hero, enemy, sc);
            case 2 -> this.playerUsePowerOnEnemy(hero, enemy, sc);
            case 3 -> this.playerUsePotion(hero);
            default -> throw new IllegalStateException("Commande non valable: " + playerChoice);
        }
        // Display remaing health point
        if(hero.isAlive()) {
            this.remainingHealthPoint(hero);
        }
        
    }


    // display strike back from the ennemi
    public void attackReturn(Hero hero, Enemy enemy, Scanner sc) {

        System.out.println();
        System.out.println(enemy.getName() + " attaque " + hero.getName());
        int diceResult = DiceRollerImpl.getInstance().throwDice();
        if(diceResult == 6) {
            System.out.println("Attaque critique ! dégâts doublés !");
        }
        int enemyDmg = enemy.attack(hero, diceResult);
        System.out.println(enemy.getName() + " inflige : " + enemyDmg + " dégâts");
        hero.takeDamages(enemyDmg);
    }


    // generate random enemy on percentage
    public Enemy generateNewEnemy() {
        int rand = (int)(Math.random()*100);
        if(rand < 61) {
            return new Goblin();
        } else if(rand < 91) {
            return new Troll();
        } else {
            return new Dragon();
        }
    }

    // display remaining health point
    public void remainingHealthPoint(Hero hero) {
        System.out.println(
                "Il vous reste " + hero.getHealth() + " points de vie"
        );
    }

}
