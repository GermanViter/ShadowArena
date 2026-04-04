package com.rpg.main;

import com.rpg.entities.Mage;
import com.rpg.entities.Warrior;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String playerName = "";
        displayMenu(sc, playerName);

        Mage mage = new Mage(playerName);
        Warrior war = new Warrior(playerName);
        String playerChoice = sc.nextLine();

        var pl = (playerChoice.equals("1")) ? mage : (playerChoice.equals("2")) ? war : null;
        System.out.println(pl);
    }

    public static void displayMenu(Scanner sc, String pl) {

        System.out.println("<======================>");
        System.out.println("Bien venu dans la Shadow Arena");
        System.out.print("Entrez votre nom : ");
        pl = sc.nextLine();
        System.out.println("Veulliez choisir votre personnage : ");
        System.out.println("[1] Mage ~~ HP : 100, ATQ : 25, SPE : regeneration 100%");
        System.out.println("[2] Warior ~~ HP : 150, ATQ : 20, SPE : attaque lourde");
        System.out.println("[Q] Quitter");
        System.out.println("<======================>");
    }
}
