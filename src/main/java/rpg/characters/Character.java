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

    // Attack another character
    public int attack(Character target, DiceRollerImpl diceRoller) {

        int damages = this.atk * diceRoller.throwDice() - target.getDef();

        if(damages > 0) {
            return damages;
        } else {
            return 0;
        }
    }

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
