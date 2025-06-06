package rpg.characters;

import rpg.services.impl.DiceRollerImpl;

public abstract class Character {

    // character name
    private String name;
    // base health
    private int health;
    // base atk
    private int atk;
    // base def
    private int def;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public Character(String name, int health, int atk, int def) {
        this.name = name;
        this.health = health;
        this.atk = atk;
        this.def = def;
    }

    // Attack another character
    public int attack(Character target) {

        int diceResult = DiceRollerImpl.getInstance().throwDice();

        double atkFactor;
        switch (diceResult) {
            case 1 -> atkFactor = 0.5;
            case 2,3,4 -> atkFactor = 1;
            case 5 -> atkFactor = 1.2;
            case 6 -> atkFactor = 2;
            default -> throw new IllegalStateException("Unexpected value: " + diceResult);
        }

        int damages = (int) Math.round(this.atk*atkFactor) - target.getDef();
        if(damages > 0) {
            return damages;
        } else {
            return 0;
        }
    }


    //


    // Take damage from another character
    public void takeDamages(int damages) {
        this.setHealth(this.getHealth() - damages);
    }

    // Check if the character is still alive
    public boolean isAlive() {
        if(this.getHealth() <= 0) {
            return false;
        }
        return true;
    }

}
