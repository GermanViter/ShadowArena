package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.BerserkerAbilities;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Berserker extends Hero implements BerserkerAbilities {

    private boolean isBerserk;
    private int berserkTurns;

    public Berserker(String name) {
        super(name, 500, 500, 200, 200, 50);
        this.isBerserk = false;
        this.berserkTurns = 0;
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.RED_BOLD + getName() + ConsoleColors.RESET
                + " charge furieusement sur " + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");

        int baseDamage = this.getDamage();
        int bonusDamage = (getMaxHP() - getCurrentHP()) / 10;

        if (bonusDamage < 0)
            bonusDamage = 0;

        int amuletteBonus = isAmuletteActive() ? 40 : 0;
        if (amuletteBonus > 0) {
            ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "L'amulette brille et renforce la rage du Berserker ! (+40 dégâts)" + ConsoleColors.RESET);
        }

        int totalDamage = baseDamage + bonusDamage + amuletteBonus;

        if (isBerserk) {
            ConsoleUtils.slowPrint(
                    ConsoleColors.RED_BOLD + "MODE BERSERK ACTIF ! Dégâts augmentés !" + ConsoleColors.RESET);
            target.takeDamage(totalDamage * 2);
            decrementBerserk();
        } else {
            target.takeDamage(totalDamage);
        }
    }

    @Override
    public void enableBerserkMode(GameCharacter target) {
        if (isBerserk) {
            ConsoleUtils
                    .slowPrint(ConsoleColors.YELLOW + getName() + " est déjà en mode Berserk !" + ConsoleColors.RESET);
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

        setCurrentHP(getCurrentHP() * 2);

        ConsoleUtils.slowPrint(
                ConsoleColors.RED_BOLD + getName()
                        + " entre en RAGE BERSERK ! HP augmentés et dégâts doublés pour 3 tours !"
                        + ConsoleColors.RESET);
    }

    private void decrementBerserk() {
        if (isBerserk) {
            berserkTurns--;
            if (berserkTurns <= 0) {
                disableBerserkMode(null, 0);
            } else {
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "(Mode Berserk : encore " + berserkTurns + " tours)"
                        + ConsoleColors.RESET);
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
