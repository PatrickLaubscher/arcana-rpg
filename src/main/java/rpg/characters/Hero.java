package rpg.characters;

public class Hero extends Character {

    private int killerCount;

    public int getKillerCount() {
        return killerCount;
    }

    public void setKillerCount(int killerCount) {
        this.killerCount = killerCount;
    }

    public Hero(String name) {
        super(name, 400, 40, 30);
    }

}
