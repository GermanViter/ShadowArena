package com.rpg.main;

import com.rpg.entities.*;
import com.rpg.core.*;
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
        if (playerChoice.toUpperCase().equals("Q")) return;

        // On utilise le polymorphisme pour traiter le joueur comme un Hero générique
        Hero pl = (playerChoice.equals("1")) ? mage : (playerChoice.equals("2")) ? war : null;
        
        if (pl == null) {
            System.out.println("Choix invalide. Fin du programme.");
            return;
        }

        // Création d'une liste de monstres pour l'arène
        Monster[] monsters = { new Goblin(), new Dragon() };

        System.out.println("\n--- Le combat commence dans la Shadow Arena ! ---");

        while (pl.isAlive()) {
            // 1. Un monstre aléatoire attaque ce tour-ci
            Monster activeMonster = monsters[rd.nextInt(monsters.length)];
            System.out.println("\n--- TOUR DE L'ENNEMI ---");
            activeMonster.attack(pl);

            if (!pl.isAlive()) {
                System.out.println("\nDOMMAGE ! " + pl.getName() + " a succombé dans l'arène...");
                break;
            }

            // 2. Tour du joueur
            System.out.println("\n--- VOTRE TOUR (HP: " + pl.getCurrentHP() + "/" + pl.getMaxHP() + ") ---");
            System.out.println("Actions : [1] Attaque Normale | [2] Capacité Spéciale");
            System.out.print("Que faites-vous ? ");
            String action = sc.nextLine();

            if (action.equals("1")) {
                pl.attack(activeMonster);
            } else if (action.equals("2")) {
                // Utilisation des capacités spéciales via les interfaces
                if (pl instanceof Mage) {
                    ((Mage) pl).regenerate();
                } else if (pl instanceof Warrior) {
                    ((Warrior) pl).heavyAttack(activeMonster);
                }
            }
            
            System.out.println("--------------------------------");
        }

        sc.close();
    }

    public static String displayMenu(Scanner sc, String pl) {
        System.out.println("<======================>");
        System.out.println("Bienvenu dans la Shadow Arena");
        System.out.print("Entrez votre nom : ");
        pl = sc.nextLine();
        System.out.println("Veuillez choisir votre personnage : ");
        System.out.println("[1] Mage ~~ HP : 100, ATQ : 25, SPE : Régénération");
        System.out.println("[2] Warrior ~~ HP : 150, ATQ : 20, SPE : Attaque Lourde");
        System.out.println("[Q] Quitter");
        System.out.println("<======================>");
        return pl;
    }
}
