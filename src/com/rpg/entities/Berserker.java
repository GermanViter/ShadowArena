package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.WariorAbilities;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Berserker extends Hero implements WariorAbilities {

    private boolean isBerserk;
    private int berserkTurns;

    public Berserker(String name) {
        super(name, 500, 500, 200, 200);
        this.isBerserk = false;
        this.berserkTurns = 0;
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.RED_BOLD + getName() + ConsoleColors.RESET
                + " charge furieusement sur " + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");

        int baseDamage = 40;
        int bonusDamage = (getMaxHP() - getCurrentHP()) / 10; 
        
        // On s'assure que le bonus ne soit pas négatif si on a plus de HP que le Max (pendant le mode Berserk)
        if (bonusDamage < 0) bonusDamage = 0;

        if (isBerserk) {
            ConsoleUtils.slowPrint(ConsoleColors.RED_BOLD + "MODE BERSERK ACTIF ! Dégâts augmentés !" + ConsoleColors.RESET);
            target.takeDamage((baseDamage + bonusDamage) * 2);
            decrementBerserk();
        } else {
            target.takeDamage(baseDamage + bonusDamage);
        }
    }

    @Override
    public void enableBerserkMode(GameCharacter target) {
        if (isBerserk) {
            ConsoleUtils.slowPrint(ConsoleColors.YELLOW + getName() + " est déjà en mode Berserk !" + ConsoleColors.RESET);
            return;
        }
        if (getMana() < 100) {
            ConsoleUtils.slowPrint(
                    ConsoleColors.YELLOW + getName() + " n'a pas assez de mana pour entrer en mode Berserk."
                            + ConsoleColors.RESET);
            return;
        }
        
        this.isBerserk = true;
        this.berserkTurns = 3;
        setMana(getMana() - 100);
        
        // Bonus de HP : On ajoute 500 HP temporaires
        setCurrentHP(getCurrentHP() + 500);
        
        ConsoleUtils.slowPrint(
                ConsoleColors.RED_BOLD + getName() + " entre en RAGE BERSERK ! HP augmentés et dégâts doublés pour 3 tours !"
                        + ConsoleColors.RESET);
    }

    private void decrementBerserk() {
        if (isBerserk) {
            berserkTurns--;
            if (berserkTurns <= 0) {
                disableBerserkMode(null, 0);
            } else {
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "(Mode Berserk : encore " + berserkTurns + " tours)" + ConsoleColors.RESET);
            }
        }
    }

    @Override
    public void disableBerserkMode(GameCharacter target, int originalHP) {
        this.isBerserk = false;
        this.berserkTurns = 0;
        
        // Si les HP actuels sont toujours au-dessus du max, on les ramène au max
        if (getCurrentHP() > getMaxHP()) {
            setCurrentHP(getMaxHP());
        }
        
        ConsoleUtils.slowPrint(
                ConsoleColors.YELLOW + getName() + " sort du mode Berserk. Ses HP reviennent à la normale."
                        + ConsoleColors.RESET);
    }

    public boolean isBerserk() {
        return isBerserk;
    }
}
