package com.rpg.main;

import com.rpg.entities.*;
import com.rpg.core.*;
import com.rpg.items.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

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
        boolean lowManaPotionGiven = false;
        boolean amuletteGiven = false;

        String playerName = "";
        playerName = displayMenu(sc, playerName);

        Hero pl = null;
        if (SaveManager.playerExists(playerName)) {
            ConsoleUtils.slowPrint(
                    ConsoleColors.YELLOW + "\nUne sauvegarde existe pour " + playerName + " !" + ConsoleColors.RESET);
            ConsoleUtils.slowPrint("Voulez-vous [1] Charger la partie ou [2] Commencer une nouvelle aventure ? ");
            String loadChoice = sc.nextLine();
            if (loadChoice.equals("1")) {
                PlayerSave savedData = SaveManager.loadPlayer(playerName);
                pl = savedData.getHero();
                defeatedMonsters = savedData.getDefeatedMonsters();
                inventory = savedData.getInventory();
                lowHealthPotionGiven = savedData.isLowHealthPotionGiven();
                lowManaPotionGiven = savedData.isLowManaPotionGiven();
                amuletteGiven = savedData.isAmuletteGiven();
                ConsoleUtils.slowPrint(ConsoleColors.GREEN + "Sauvegarde chargée avec succès !" + ConsoleColors.RESET);
            }
        }

        if (pl == null) {
            Mage mage = new Mage(playerName);
            Knight knight = new Knight(playerName);
            Berserker berserk = new Berserker(playerName);

            String playerChoice = sc.nextLine();
            if (playerChoice.toUpperCase().equals("Q"))
                return;

            pl = (playerChoice.equals("1")) ? mage
                    : (playerChoice.equals("2")) ? knight : (playerChoice.equals("3")) ? berserk : null;

            if (pl == null) {
                ConsoleUtils.slowPrint(ConsoleColors.RED + "Choix invalide. Fin du programme." + ConsoleColors.RESET);
                return;
            }
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
            if (!amuletteGiven && rd.nextInt(20) == 0) {
                amuletteGiven = true;
                Amulette amulette = new Amulette();
                inventory.add(amulette);
                ConsoleUtils.slowPrint(
                        "\n" + ConsoleColors.YELLOW + "--- OBJET LÉGENDAIRE TROUVÉ ! ---" + ConsoleColors.RESET);
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "Vous trouvez une " + amulette.getName() + " au sol ! "
                        + amulette.getDescription() + ConsoleColors.RESET);
            }

            if (pl.isAmuletteActive()) {
                pl.setCurrentHP(pl.getCurrentHP() - 10);
                ConsoleUtils.slowPrint(
                        ConsoleColors.RED + "L'amulette brille d'une lueur sombre et vous ronge la vie... (-10 HP)"
                                + ConsoleColors.RESET);
                if (!pl.isAlive()) {
                    ConsoleUtils.slowPrint("\n" + ConsoleColors.RED_BOLD + pl.getName()
                            + " a succombé à la malédiction de l'amulette..." + ConsoleColors.RESET);
                    break;
                }
            }

            if (!lowHealthPotionGiven && pl.getCurrentHP() < (pl.getMaxHP() / 2)) {
                inventory.add(new Potion());
                lowHealthPotionGiven = true;
                ConsoleUtils.slowPrint(
                        "\n" + ConsoleColors.YELLOW + "--- VOS FORCES DÉCLINENT ! ---" + ConsoleColors.RESET);
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW
                        + "Vous trouvez une potion de soin au sol ! Utilisez-la sagement." + ConsoleColors.RESET);
            }

            if (!lowManaPotionGiven && pl.getMana() < (pl.getMaxMana() / 2)) {
                inventory.add(new ManaPotion());
                lowManaPotionGiven = true;
                ConsoleUtils.slowPrint(
                        "\n" + ConsoleColors.YELLOW + "--- VOTRE MANA DIMINUE ! ---" + ConsoleColors.RESET);
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW
                        + "Vous trouvez une potion de mana au sol ! Utilisez-la pour vos capacités spéciales."
                        + ConsoleColors.RESET);
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

            Instant start = Instant.now();

            String action = sc.nextLine();

            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            if (timeElapsed.toSeconds() > 15) {
                ConsoleUtils.slowPrint(ConsoleColors.RED
                        + "Trop lent ! Le monstre en profite pour attaquer en premier !" + ConsoleColors.RESET);
                activeMonster.attack(pl);
                if (!pl.isAlive()) {
                    ConsoleUtils.slowPrint("\n" + ConsoleColors.RED_BOLD + "DOMMAGE ! " + pl.getName()
                            + " a succombé dans l'arène..." + ConsoleColors.RESET);
                    break;
                }
                continue;
            }

            if (action.equals("1")) {
                pl.attack(activeMonster);
            } else if (action.equals("2")) {
                if (pl instanceof Mage) {
                    ((Mage) pl).regenerate();
                } else if (pl instanceof Knight) {
                    ((Knight) pl).heavyAttack(activeMonster);
                } else if (pl instanceof Berserker) {
                    ((Berserker) pl).enableBerserkMode(activeMonster);
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
                pl.gainExperience(50);
                ConsoleUtils.slowPrint("\n" + ConsoleColors.GREEN_BOLD + "FÉLICITATIONS ! Le " + activeMonster.getName()
                        + " a été vaincu !" + ConsoleColors.RESET);
                defeatedMonsters++;

                ConsoleUtils
                        .slowPrint(ConsoleColors.GREEN + "Vous gagnez 50 points d'expérience !" + ConsoleColors.RESET);

                Grenade loot = new Grenade();
                inventory.add(loot);
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "Vous avez ramassé une " + loot.getName()
                        + " sur le cadavre !" + ConsoleColors.RESET);

                if (defeatedMonsters < 3) {
                    ConsoleUtils.slowPrint(ConsoleColors.YELLOW
                            + "Un nouveau monstre apparaît pour venger son camarade !" + ConsoleColors.RESET);
                    activeMonster = getRandomMonster(rd);
                }

                SaveManager.savePlayer(new PlayerSave(pl, defeatedMonsters, inventory,
                    pl.isAmuletteActive(), lowHealthPotionGiven, lowManaPotionGiven, amuletteGiven), playerName);
            } else {
                ConsoleUtils
                        .slowPrint("\n" + ConsoleColors.YELLOW_BOLD + "--- TOUR DE L'ENNEMI ---" + ConsoleColors.RESET);
                activeMonster.attack(pl);

                if (!pl.isAlive()) {
                    ConsoleUtils.slowPrint("\n" + ConsoleColors.RED_BOLD + "DOMMAGE ! " + pl.getName()
                            + " a succombé dans l'arène..." + ConsoleColors.RESET);
                    break;
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
                ConsoleColors.BLUE + "[1] Mage      ~~ HP: 400 | ATQ: 25 | SPE: Régénération" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.GREEN + "[2] Knight    ~~ HP: 450 | ATQ: 20 | SPE: Attaque Lourde" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.RED + "[3] Berserker ~~ HP: 500 | ATQ: 40+ | SPE: Rage Berserk" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(ConsoleColors.RED + "[Q] Quitter" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(
                ConsoleColors.CYAN + "<=============================================>" + ConsoleColors.RESET);
        return pl;
    }
}
