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

        Hero pl = (playerChoice.equals("1")) ? mage : (playerChoice.equals("2")) ? war : null;
        
        if (pl == null) {
            System.out.println(ConsoleColors.RED + "Choix invalide. Fin du programme." + ConsoleColors.RESET);
            return;
        }

        Monster[] monsters = { new Goblin(), new Dragon() };

        System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "--- Le combat commence dans la Shadow Arena ! ---" + ConsoleColors.RESET);

        while (pl.isAlive()) {
            Monster activeMonster = monsters[rd.nextInt(monsters.length)];
            System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "--- TOUR DE L'ENNEMI ---" + ConsoleColors.RESET);
            activeMonster.attack(pl);

            if (!pl.isAlive()) {
                System.out.println("\n" + ConsoleColors.RED_BOLD + "DOMMAGE ! " + pl.getName() + " a succombé dans l'arène..." + ConsoleColors.RESET);
                break;
            }

            System.out.println("\n" + ConsoleColors.CYAN_BOLD + "--- VOTRE TOUR (HP: " + pl.getCurrentHP() + "/" + pl.getMaxHP() + ") ---" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "Actions : [1] Attaque Normale | [2] Capacité Spéciale" + ConsoleColors.RESET);
            System.out.print("Que faites-vous ? ");
            String action = sc.nextLine();

            if (action.equals("1")) {
                pl.attack(activeMonster);
            } else if (action.equals("2")) {
                if (pl instanceof Mage) {
                    ((Mage) pl).regenerate();
                } else if (pl instanceof Warrior) {
                    ((Warrior) pl).heavyAttack(activeMonster);
                }
            }
            
            System.out.println(ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
        }

        sc.close();
    }

    public static String displayMenu(Scanner sc, String pl) {
        System.out.println(ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE_BOLD + "       Bienvenue dans la SHADOW ARENA" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.WHITE_BOLD + "Entrez votre nom de héros : " + ConsoleColors.RESET);
        pl = sc.nextLine();
        System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "Veuillez choisir votre personnage :" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "[1] Mage    ~~ HP: 100 | ATQ: 25 | SPE: Régénération" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "[2] Warrior ~~ HP: 150 | ATQ: 20 | SPE: Attaque Lourde" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "[Q] Quitter" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        return pl;
    }
}
