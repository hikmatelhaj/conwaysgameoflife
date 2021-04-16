package sample.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class World {

    private Cel[][] bord;
    private final int width;
    private final int height;

    public World(File file) throws FileNotFoundException {
        WorldDAO data = new WorldDAO(file);

        width = data.getReadWidth();
        height = data.getReadHeight();
        bord = new Cel[height][width];
        List<Character> karakterstatussen = new ArrayList<>();
        karakterstatussen = data.getLijst();
        int elementInLijst = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (karakterstatussen.get(elementInLijst) == '.') {
                    bord[i][j] = new Cel(0);
                } else {
                    bord[i][j] = new Cel(1);
                }
                elementInLijst++;
            }
        }
    }

    public World(int width, int height) {
        bord = new Cel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bord[i][j] = new Cel(0);
            }
        }
        this.width = width;
        this.height = height;

    }



    public void toggleCell(int rijen, int kolommen) {
        if (bord[kolommen][rijen].getStatus()) {
            bord[kolommen][rijen].setStatus(false);
        } else {
            bord[kolommen][rijen].setStatus(true);
        }

    }

    public void saveToFile(final File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.append(Integer.toString(width));
        writer.append(' ');
        writer.append(Integer.toString(height));
        writer.append('\n');
        int teller = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                teller++;
                if (bord[i][j].getStatus()) {
                    writer.append('O');
                } else {
                    writer.append('.');
                }
                if (teller == width) { //wanneer de teller = breedte, dan moet er overgegaan worden naar een nieuwe lijn
                    teller = 0;
                    writer.append('\n');
                }
            }
        }
        writer.close();
    }

    private Cel[][] copy(Cel[][] cel) {                   //kopie maken van bord omdat bord niet mag aangepast worden tijdens het uitvoeren
        Cel[][] kopie = new Cel[height][width];          // van de methode nextgeneration
        for (int iKopie = 0; iKopie < height; iKopie++) {
            for (int jKopie = 0; jKopie < width; jKopie++) {
                if (cel[iKopie][jKopie].getStatus()) {
                    kopie[iKopie][jKopie] = new Cel(1);
                }
                else {
                    kopie[iKopie][jKopie] = new Cel(0);
                }
            }
        }
        return kopie;
    }
    public void nextGeneration() {
        Cel[][] kopie = copy(bord);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int aantalLevendeBuren = 0;
                int coordinaatX = 0, coordinaatY = 0;
                for (int x = -1; x <= 1; x++) {       // deze 2 for lussen controleren alle mogelijke buren
                    for (int y = -1; y <= 1; y++) {
                        coordinaatX = j + y;
                        coordinaatY = i + x;
                        try {                       //als er geen buren mogelijk zijn
                            if (kopie[coordinaatY][coordinaatX].getStatus()) {
                                aantalLevendeBuren++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }

                }
                evolutie(aantalLevendeBuren, j, i);
            }
        }
    }


    public void evolutie(int aantal, int x, int y) {
        if (bord[y][x].getStatus()) {
            aantal--;                       //in de vorige methode wordt de cel zelf altijd meegeteld
        }
            if (bord[y][x].getStatus() && aantal < 2) {
                toggleCell(x, y);

            } else if (bord[y][x].getStatus() && aantal > 3) {
                toggleCell(x, y);

            } else if (!bord[y][x].getStatus() && aantal == 3) {
                toggleCell(x, y);

        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAliveAt(int x, int y) {
        return bord[y][x].getStatus();
    }

    public void randomCells() {
        Random r = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int willekeur = r.nextInt(2);
                if (willekeur == 1) {
                    bord[i][j] = new Cel(1);
                } else {
                    bord[i][j] = new Cel(0);
                }
            }
        }
    }

    public void printBord() {                   //deze methode dient om een bord te printen in de commandlineapplicatie
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (bord[i][j].getStatus()) {
                    System.out.print("O");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public Cel[][] getBord() { return bord; }

}
