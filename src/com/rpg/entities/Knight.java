package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.KnightAbilities;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Knight extends Hero implements KnightAbilities {

    public Knight(String name) {
        super(name, 450, 450, 600, 600, 35);
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils
                .slowPrint(ConsoleColors.BLUE_BOLD + getName() + ConsoleColors.RESET + " abat une épée puissante sur "
                        + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");
        int damage = this.getDamage();
        if (isAmuletteActive()) {
            damage += 40;
            ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "L'amulette brille et renforce l'attaque ! (+40 dégâts)" + ConsoleColors.RESET);
        }
        target.takeDamage(damage);

    }

    @Override
    public void heavyAttack(GameCharacter target) {
        if (this.getMana() >= 80) {
            ConsoleUtils.slowPrint(
                    ConsoleColors.BLUE_BOLD + getName() + ConsoleColors.RED_BOLD + " FAIT UNE ATTAQUE LOURDE SUR "
                            + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");
            int damage = 85;
            if (isAmuletteActive()) {
                damage += 40;
                ConsoleUtils.slowPrint(ConsoleColors.YELLOW + "L'amulette brille et renforce l'attaque lourde ! (+40 dégâts)" + ConsoleColors.RESET);
            }
            target.takeDamage(damage);
            this.setMana(this.getMana() - 80);
        } else {
            ConsoleUtils.slowPrint(
                    ConsoleColors.YELLOW + getName() + " n'a pas assez de mana pour faire une attaque lourde."
                            + ConsoleColors.RESET);
        }
    }
}
