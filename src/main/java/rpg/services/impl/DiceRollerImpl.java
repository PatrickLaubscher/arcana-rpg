package rpg.services.impl;

import rpg.services.DiceRoller;

public class DiceRollerImpl implements DiceRoller {

    private static DiceRollerImpl instance;

    public static DiceRollerImpl getInstance() {
        if (instance == null) {
            instance = new DiceRollerImpl();
        }
        return instance;
    }

    // Similate throwing a 6 faces dice
    public int throwDice() {
        return (int)(6.0 * Math.random()) + 1;
    }

}
