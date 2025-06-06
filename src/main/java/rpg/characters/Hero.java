package rpg.characters;

import rpg.interfaces.SpecialPower;
import rpg.services.exceptions.CheckManaException;
import rpg.services.exceptions.CheckPotionException;

public class Hero extends Character implements SpecialPower {

    private int killerCount;
    private int mana;
    private int potionNb;
    private int powerMana = 10;

    public int getKillerCount() {
        return killerCount;
    }

    public void setKillerCount(int killerCount) {
        this.killerCount = killerCount;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPotionNb() {
        return potionNb;
    }

    public void setPotionNb(int potionNb) {
        this.potionNb = potionNb;
    }

    public int getPowerMana() {
        return powerMana;
    }

    public void setPowerMana(int powerMana) {
        this.powerMana = powerMana;
    }

    public Hero(String name) {
        super(name, 300, 70, 45);
        this.mana = 50;
        this.potionNb = 1;
    }

    // use special power and decrease mana points
    public int useSpecialPower(Enemy target, int diceResult) {

        // use mana
        this.setMana(this.getMana() - powerMana);

        double atkFactor;
        switch (diceResult) {
            case 1,2,3,4,5 -> atkFactor = 1;
            case 6 -> atkFactor = 2;
            default -> throw new IllegalStateException("Unexpected value: " + diceResult);
        }

        int powerAtk = 150;

        int damages = (int) Math.round(powerAtk*atkFactor) - target.getDef();
        if(damages > 0) {
            return damages;
        } else {
            return 0;
        }

    }

    // Check mana balance and throw exception
    public void checkManaBalance(int powerMana) throws CheckManaException {
        if(this.getMana() < powerMana) {
            throw new CheckManaException("Désolé, vous n'avez plus suffisamment de mana.");
        }
    }

    // use potion to recover max health
    public void useHealthPotion() {
        this.setPotionNb(this.getPotionNb() - 1);
        this.setHealth(this.getMaxHealth());
    }

    // Check potion number and throw exception
    public void checkRemainingPotion() throws CheckPotionException {
        if(this.getPotionNb() <= 0) {
            throw new CheckPotionException("Désolé, vous n'avez plus de potions.");
        }

    }

}
