package sample.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CommandLineApplication {
    public static void main(String []args) throws InterruptedException {
        String bestandsnaam;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welkom bij Conway's Game of Life (by Hikmat and Xander)");
        if(args.length == 1) {
            System.out.println("Bedankt voor het ingeven van een bestandsnaam op de commandline");
            bestandsnaam = args[0];
        }
        else{
            System.out.println("U hebt geen bestandsnaam meegegeven op de commandline of te veel argumenten");
            System.out.println("Geef een nieuw bestandsnaam in");
            bestandsnaam = sc.nextLine();
        }
        boolean isGelukt = false;
        File file;
        while(!isGelukt) {
            try {
                file = new File(bestandsnaam);
                WorldAlternateRules w = new WorldAlternateRules(file);
                isGelukt = true;
                int teller = 0;
                System.out.println("Als u een limiet voor het aantal simulaties wilt instellen, druk op 1. Indien u iets anders ingeeft, staat de limiet op 100");
                String getal = sc.next();
                if (getal.equals("1")) {
                    System.out.println("Geef het aantal simulaties nu in: ");
                    teller = sc.nextInt();
                }
                else {
                    teller = 100;
                }
                System.out.println("De simulatie wordt gestart. BELANGRIJK! Druk op CTRL + c om het programma te stoppen");
                System.out.println("De simulatie start in 5 seconden");
                TimeUnit.SECONDS.sleep(5);

                while (teller > 0) {
                    w.printBord();
                    w.nextGeneration();
                    TimeUnit.SECONDS.sleep(2);
                    teller--;
                }
            } catch (FileNotFoundException e) {
                System.out.println("Bestand niet gevonden");
                System.out.println("Geef een nieuw bestandsnaam in: ");
                bestandsnaam = sc.nextLine();
            }
            catch (IndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Gegevens in het bestand komen niet overeen met de vereisten van het spel");
                System.out.println("Geef een nieuw bestandsnaam in: ");
                bestandsnaam = sc.nextLine();
            }
        }
    }
}
