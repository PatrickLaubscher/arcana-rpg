package rpg;

import rpg.characters.Character;
import rpg.characters.Enemy;
import rpg.characters.Hero;
import rpg.services.impl.DiceRollerImpl;

public class Main {


    public static void main(String[] args) {

        Hero hero = new Hero("Henry");
        Enemy enemy = new Enemy();
        DiceRollerImpl dice = new DiceRollerImpl();

        System.out.println(hero.attack(enemy, dice));


    }


}