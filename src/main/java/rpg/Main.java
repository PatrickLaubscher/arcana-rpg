package rpg;

import rpg.characters.Enemy;
import rpg.characters.Hero;
import rpg.services.impl.CombatManagerImpl;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Hero hero = new Hero("Henry");
        Enemy enemy = new Enemy();
        CombatManagerImpl turn = new CombatManagerImpl();
        Scanner sc = new Scanner(System.in);

        while(hero.isAlive()) {

            if(!enemy.isAlive()){
                enemy = turn.generateNewEnemy();
            }

            turn.combatTurn(hero, enemy, sc);

        }

        System.out.println("Fin de partie");
        sc.close();
    }


}