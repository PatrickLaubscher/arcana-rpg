package rpg;

import rpg.characters.Enemy;
import rpg.characters.Hero;
import rpg.services.impl.CombatManagerImpl;
import rpg.services.impl.ScoreFileDataImpl;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        CombatManagerImpl turn = new CombatManagerImpl();
        ScoreFileDataImpl file = new ScoreFileDataImpl();
        Hero hero = new Hero("Henry");
        Enemy enemy = turn.generateNewEnemy();
        System.out.println("Vous avancez et un " + enemy.getName() + " se trouve devant vous.");
        Scanner sc = new Scanner(System.in);

        while(hero.isAlive()) {

            if(!enemy.isAlive()){
                enemy = turn.generateNewEnemy();
                System.out.println("Vous avancez et un " + enemy.getName() + " se trouve devant vous.");
            }
            turn.combatTurn(hero, enemy, sc);

        }

        System.out.println(hero.getName() + " est tombé.");
        System.out.println("vous avez tué " + hero.getKillerCount() + " ennemis");
        file.createNewFile(hero);
        sc.close();
    }


}