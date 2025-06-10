package rpg.game;

import rpg.characters.Enemy;
import rpg.characters.Hero;
import rpg.services.impl.CombatManagerImpl;
import rpg.services.impl.ScoreFileDataImpl;

import java.util.Scanner;

public class Game {

    private Hero hero;
    private CombatManagerImpl combatManager;
    private ScoreFileDataImpl fileManager;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public CombatManagerImpl getCombatManager() {
        return combatManager;
    }

    public void setCombatManager(CombatManagerImpl combatManager) {
        this.combatManager = combatManager;
    }

    public ScoreFileDataImpl getFileManager() {
        return fileManager;
    }

    public void setFileManager(ScoreFileDataImpl fileManager) {
        this.fileManager = fileManager;
    }

    // Start a new game
    public Game() {
        Scanner sc = new Scanner(System.in);
        this.combatManager = new CombatManagerImpl();
        this.fileManager = new ScoreFileDataImpl();
        this.inputNewHero(sc);
        this.gameLoop(sc);
    }

    // Create a new hero and set name
    public void inputNewHero(Scanner sc) {

        System.out.println(" _   |~  _\n" +
                        "[_]--'--[_]\n" +
                        "|'|\"\"`\"\"|'|\n" +
                        "| | /^\\ | |\n" +
                        "|_|_|I|_|_|");
        System.out.println("*** Bienvenue dans Arcana ! *** ");
        System.out.println("Veuillez entrer le nom de votre personnage: ");
        String name = sc.nextLine();
        Hero hero = new Hero(name);
        setHero(hero);

    }


    // Game loop management
    public void gameLoop(Scanner sc) {

        Enemy enemy = combatManager.generateNewEnemy();
        System.out.println();
        System.out.println("*****");
        System.out.println("Vous avancez et un " + enemy.getName() + " se trouve devant vous.");
        System.out.println("*****");
        System.out.println();

        while(hero.isAlive()) {

            if(!enemy.isAlive()){
                enemy = combatManager.generateNewEnemy();
                System.out.println();
                System.out.println("*****");
                System.out.println("Vous avancez et un " + enemy.getName() + " se trouve devant vous.");
                System.out.println("*****");
                System.out.println();
            }
            combatManager.combatTurn(hero, enemy, sc);

        }
        System.out.println();
        System.out.println("\n" +
                "\n" +
                "        _.---,._,'\n" +
                "       /' _.--.<\n" +
                "         /'     `'\n" +
                "       /' _.---._____\n" +
                "       \\.'   ___, .-'`\n" +
                "           /'    \\\\             .\n" +
                "         /'       `-.          -|-\n" +
                "        |                       |\n" +
                "        |                   .-'~~~`-.\n" +
                "        |                 .'         `.\n" +
                "        |                 |  R  I  P  |\n" +
                "        |                 |           |\n" +
                "        |                 |           |\n" +
                "         \\              \\\\|           |//\n" +
                "   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "                        art by jgs\n");
        System.out.println("              "+ hero.getName() + " est tombé.");
        System.out.println();
        System.out.println("Bravo, vous avez tué " + hero.getKillerCount() + " ennemis");
        fileManager.createNewFile(hero);
        sc.close();

    }

}
