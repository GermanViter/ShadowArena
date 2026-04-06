package com.rpg.main;

import com.rpg.entities.*;
import com.rpg.core.*;
import com.rpg.items.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        /*
         * ####################################################
         * #####Etat initial du jeux
         * ####################################################
         */
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        int defeatedMonsters = 0;
        ArrayList<GameItem> inventory = new ArrayList<>();
        boolean lowHealthPotionGiven = false;

        String playerName = "";
        playerName = displayMenu(sc, playerName);

        Mage mage = new Mage(playerName);
        Warrior war = new Warrior(playerName);

        String playerChoice = sc.nextLine();
        if (playerChoice.toUpperCase().equals("Q"))
            return;

        Hero pl = (playerChoice.equals("1")) ? mage : (playerChoice.equals("2")) ? war : null;

        if (pl == null) {
            ConsoleUtils.slowPrint(ConsoleColors.RED + "Choix invalide. Fin du programme." + ConsoleColors.RESET);
            return;
        }

        ConsoleUtils.slowPrint("\n" + ConsoleColors.PURPLE_BOLD + "--- Le combat commence dans la Shadow Arena ! ---"
                + ConsoleColors.RESET);

        Monster activeMonster = getRandomMonster(rd);

        /*
         * ####################################################
         * #####Boucle de combat principale
         * ####################################################
         */

        while (pl.isAlive()) {
            if (!lowHealthPotionGiven && pl.getCurrentHP() < (pl.getMaxHP() / 2)) {
                inventory.add(new Potion());
                lowHealthPotionGiven = true;
                ConsoleUtils.slowPrint(
                        "\n" + ConsoleColors.YELLOW + "--- VOS FORCES DÉCLINENT ! ---" + ConsoleColors.RESET);
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW
                        + "Vous trouvez une potion de soin au sol ! Utilisez-la sagement." + ConsoleColors.RESET);
            }

            ConsoleUtils.slowPrint("\n" + ConsoleColors.YELLOW_BOLD + "--- TOUR DE L'ENNEMI ---" + ConsoleColors.RESET);
            activeMonster.attack(pl);

            if (!pl.isAlive()) {
                ConsoleUtils.slowPrint("\n" + ConsoleColors.RED_BOLD + "DOMMAGE ! " + pl.getName()
                        + " a succombé dans l'arène..." + ConsoleColors.RESET);
                break;
            }

            ConsoleUtils.slowPrint("\n" + ConsoleColors.CYAN_BOLD + "--- VOTRE TOUR (HP: " + pl.getCurrentHP() + "/"
                    + pl.getMaxHP() + ") (MANA: " + pl.getMana() + "/" + pl.getMaxMana() + ") ---"
                    + ConsoleColors.RESET);

            ConsoleUtils.slowPrint(ConsoleColors.WHITE_BOLD
                    + "Actions : [1] Attaque Normale | [2] Capacité Spéciale | [3] Se Défendre" + ConsoleColors.RESET);

            if (!inventory.isEmpty()) {
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "Items disponibles : " + ConsoleColors.RESET);
                for (int i = 0; i < inventory.size(); i++) {
                    ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "  [" + (i + 4) + "] " + inventory.get(i).getName()
                            + " (" + inventory.get(i).getDescription() + ")" + ConsoleColors.RESET);
                }
            }

            ConsoleUtils.slowPrintNoLine("Que faites-vous ? ");
            String action = sc.nextLine();

            if (action.equals("1")) {
                pl.attack(activeMonster);
            } else if (action.equals("2")) {
                if (pl instanceof Mage) {
                    ((Mage) pl).regenerate();
                } else if (pl instanceof Warrior) {
                    ((Warrior) pl).heavyAttack(activeMonster);
                }
            } else if (action.equals("3")) {
                pl.defend();
            } else {
                try {
                    int itemIndex = Integer.parseInt(action) - 4;
                    if (itemIndex >= 0 && itemIndex < inventory.size()) {
                        GameItem itemToUse = inventory.get(itemIndex);
                        itemToUse.use(pl, activeMonster);
                        inventory.remove(itemIndex);
                    } else {
                        ConsoleUtils.slowPrint(ConsoleColors.RED + "Action ou item invalide !" + ConsoleColors.RESET);
                    }
                } catch (NumberFormatException e) {
                    ConsoleUtils.slowPrint(ConsoleColors.RED + "Entrée invalide !" + ConsoleColors.RESET);
                }
            }

            if (!activeMonster.isAlive()) {
                ConsoleUtils.slowPrint("\n" + ConsoleColors.GREEN_BOLD + "FÉLICITATIONS ! Le " + activeMonster.getName()
                        + " a été vaincu !" + ConsoleColors.RESET);
                defeatedMonsters++;

                Grenade loot = new Grenade();
                inventory.add(loot);
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "Vous avez ramassé une " + loot.getName()
                        + " sur le cadavre !" + ConsoleColors.RESET);

                if (defeatedMonsters < 3) {
                    ConsoleUtils.slowPrint(ConsoleColors.YELLOW
                            + "Un nouveau monstre apparaît pour venger son camarade !" + ConsoleColors.RESET);
                    activeMonster = getRandomMonster(rd);
                }
            }

            if (defeatedMonsters >= 3) {
                ConsoleUtils.slowPrint("\n" + ConsoleColors.CYAN_BOLD
                        + "***********************************************" + ConsoleColors.RESET);
                ConsoleUtils.slowPrint(ConsoleColors.GREEN_BOLD + "VICTOIRE TOTALE ! " + pl.getName()
                        + " a survécu à 3 vagues et triomphé de la Shadow Arena !" + ConsoleColors.RESET);
                ConsoleUtils.slowPrint(ConsoleColors.CYAN_BOLD + "***********************************************"
                        + ConsoleColors.RESET);
                break;
            }
            ConsoleUtils.slowPrint(ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
        }

        sc.close();
    }

    public static Monster getRandomMonster(Random rd) {
        int choice = rd.nextInt(5);
        switch (choice) {
            case 0:
                return new Goblin();
            case 1:
                return new Dragon();
            case 2:
                return new Troll();
            case 3:
                return new GiantSnake();
            case 4:
                return new Cerberus();
            default:
                return new Goblin();
        }
    }

    public static String displayMenu(Scanner sc, String pl) {
        ConsoleUtils.slowPrint(
                ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        ConsoleUtils
                .slowPrint(ConsoleColors.PURPLE_BOLD + "       Bienvenue dans la SHADOW ARENA" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        ConsoleUtils.slowPrintNoLine(ConsoleColors.WHITE_BOLD + "Entrez votre nom de héros : " + ConsoleColors.RESET);
        pl = sc.nextLine();
        ConsoleUtils.slowPrint(
                "\n" + ConsoleColors.YELLOW_BOLD + "Veuillez choisir votre personnage :" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.BLUE + "[1] Mage    ~~ HP: 400 | ATQ: 25 | SPE: Régénération" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.GREEN + "[2] Warrior ~~ HP: 450 | ATQ: 20 | SPE: Attaque Lourde" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(ConsoleColors.RED + "[Q] Quitter" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        return pl;
    }
}
