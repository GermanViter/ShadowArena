package com.rpg.main;

import com.rpg.entities.Mage;
import com.rpg.entities.Warrior;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        String playerName = "";
        playerName = displayMenu(sc, playerName);
        Mage mage = new Mage(playerName);
        Warrior war = new Warrior(playerName);
        String playerChoice = sc.nextLine();

        if (playerChoice.toUpperCase().equals("Q")) {
            return;
        }

        var pl = (playerChoice.equals("1")) ? mage : (playerChoice.equals("2")) ? war : null;
        while (true) {
            if (pl == null) {
                System.out.print("Choisisses 1 ou 2 :");
                pl = (playerChoice.equals("1")) ? mage : (playerChoice.equals("2")) ? war : null;
                continue;
            }

        }
    }

    public static String displayMenu(Scanner sc, String pl) {

        System.out.println("<======================>");
        System.out.println("Bien venu dans la Shadow Arena");
        System.out.print("Entrez votre nom : ");
        pl = sc.nextLine();
        System.out.println("Veulliez choisir votre personnage : ");
        System.out.println("[1] Mage ~~ HP : 100, ATQ : 25, SPE : regeneration 100%");
        System.out.println("[2] Warior ~~ HP : 150, ATQ : 20, SPE : attaque lourde");
        System.out.println("[Q] Quitter");
        System.out.println("<======================>");
        return pl;
    }
}
