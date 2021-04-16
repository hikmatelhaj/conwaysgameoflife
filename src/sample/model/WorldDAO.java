package sample.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorldDAO {
    private final List<Character> lijst;
    private final int readWidth;
    private final int readHeight;


    public WorldDAO(File file) throws FileNotFoundException {
        lijst = new ArrayList<>();
        String lijn;
        char kar;
        Scanner sc = new Scanner(file);
        readWidth = sc.nextInt();
        readHeight = sc.nextInt();
        while (sc.hasNext()) {
            lijn = sc.nextLine();
            for (int i = 0; i < lijn.length(); i++) {
                kar = lijn.charAt(i);
                lijst.add(kar);
            }
        }
        sc.close();
    }

    public List<Character> getLijst() {
        return lijst;
    }
    public int getReadWidth() {
        return readWidth;
    }
    public int getReadHeight() {
        return readHeight;
    }

}


