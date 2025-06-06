package rpg.characters;

public abstract class Character {

    private String name;
    private int health;
    private int atk;
    private int def;

    // Attack another character
    public abstract void attack(Character target);

    // Take damage from another character
    public abstract void damage(int health);

    // Check if character is still alive
    public abstract void isAlive();

}
