package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.MageAbilities;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Mage extends Hero implements MageAbilities {

    public Mage(String name) {
        super(name, 400, 400, 600, 600);
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.CYAN_BOLD + getName() + ConsoleColors.RESET
                + " lance une boule de feu sur " + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");
        target.takeDamage(40);
    }

    @Override
    public void regenerate() {
        if (this.getCurrentHP() < this.getMaxHP() && this.getMana() >= 80) {
            ConsoleUtils.slowPrint(
                    ConsoleColors.GREEN_BOLD + getName() + " utilise la magie pour se soigner!" + ConsoleColors.RESET);
            int healedHP = this.getCurrentHP() + (this.getMaxHP() / 2);
            if (healedHP > this.getMaxHP()) healedHP = this.getMaxHP();
            this.setCurrentHP(healedHP);
            this.setMana(this.getMana() - 80);
        } else if (this.getMana() < 80) {
            ConsoleUtils.slowPrint(
                    ConsoleColors.YELLOW + getName() + " n'a pas assez de mana pour se régénérer."
                            + ConsoleColors.RESET);
        }
    }
}
