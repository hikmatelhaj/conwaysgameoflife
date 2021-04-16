package sample.model;

import java.io.File;
import java.io.FileNotFoundException;

public class WorldAlternateRules extends World {

    public WorldAlternateRules(File file) throws FileNotFoundException {
        super(file);
    }

    public WorldAlternateRules(int x, int y) {
        super(x, y);
    }


    //Open magneten.txt bij deze settings
    //Verander new World naar new WorldAlternateRule om andere regels te kunnen toepassen in Main.java
    @Override
    public void evolutie(int aantal, int x, int y) {
        Cel[][] bord = super.getBord();
        if (bord[y][x].getStatus()) {
            aantal--;
        }
        if (bord[y][x].getStatus() && aantal < 4) { //sterft als aantal kleiner is dan x
            toggleCell(x, y);

        } else if (bord[y][x].getStatus() && aantal > 3) { // sterft als aantal groter is dan x
            toggleCell(x, y);

        } else if (!bord[y][x].getStatus() && aantal == 2) { // leeft als aantal = x
            toggleCell(x, y);

        }
    }
}
