package rpg.services.impl;

import rpg.characters.Hero;
import rpg.services.ScoreFileData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScoreFileDataImpl implements ScoreFileData {

    public void createNewFile(Hero hero) {

        String fileName = "scores.txt";
        List<String> lignes = new ArrayList<>();

        // add student
        lignes.add(" *** ");
        lignes.add("Palmares de : " + hero.getName());
        lignes.add(hero.getKillerCount() + "ennemi(s) vaincu(s)");
        lignes.add(" *** ");

        // process creation file and writting
        try {
            Files.write(Paths.get(fileName), lignes);
        } catch (IOException e) {
            System.out.println("Error during writting process : " + e.getMessage());
        }

    }

}
