package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final String[] band = {"< 30", "30 - 60", "60 - 120", "120 - 180", ">= 180"};

    public static void main(String[] args) {

        try (FileReader fr = new FileReader(args[0])) {
            BufferedReader br = new BufferedReader(fr);
            
            Map<String, List<Game>> gamesList = br.lines()
                .skip(1)
                .map(lines -> lines.trim().split(","))
                .map(fields -> new Game
                    (fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[6]), Integer.parseInt(fields[7])))
                .collect(Collectors.groupingBy(games -> games.getBand()));

            File txt = new File("./games.txt");
            FileWriter fw = new FileWriter(txt);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0; i < band.length; i++) {
                String category = band[i];
                bw.write(String.format("Duration of game played: %s mins\n", category));
                for (Game game : gamesList.get(category)) {
                    bw.write(String.format("\t%s, published in %d\n", game.getName(), game.getYear()));
                }
            }

            bw.close();

       } catch (IOException e) {
            System.out.println("Cannot read file");
       }
    }
}