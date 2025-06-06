package rpg.characters;

import rpg.interfaces.SpecialPower;

public class Hero extends Character implements SpecialPower {

    private int killerCount;
    private int mana;

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

    public Hero(String name) {
        super(name, 400, 40, 30);
        this.mana = 50;
    }

    public int useSpecialPower(Enemy target, int diceResult) {

        double atkFactor;
        switch (diceResult) {
            case 1,2,3,4,5 -> atkFactor = 1;
            case 6 -> atkFactor = 2;
            default -> throw new IllegalStateException("Unexpected value: " + diceResult);
        }

        int powerAtk = 100;
        int powerMana = 10;

        // Use of mana points
        this.setMana(this.getMana() - powerMana);

        int damages = (int) Math.round(powerAtk*atkFactor) - target.getDef();
        if(damages > 0) {
            return damages;
        } else {
            return 0;
        }

    }

}
