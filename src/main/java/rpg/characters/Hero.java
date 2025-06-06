package rpg.characters;

import rpg.interfaces.SpecialPower;

public class Hero extends Character {

    private String name;
    private int health;
    private int atk;
    private int def;

    public Hero(String name) {
        this.name = name;
        this.health = 100;
        this.atk = 20;
        this.def = 40;
    }


}
