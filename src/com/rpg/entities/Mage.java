package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.Regenerable;
import com.rpg.core.ConsoleColors;

public class Mage extends Hero implements Regenerable {

    public Mage(String name) {
        super(name, 400, 400, false);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(ConsoleColors.CYAN_BOLD + getName() + ConsoleColors.RESET + " lance une boule de feu sur "
                + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");
        target.takeDamage(25);
    }

    @Override
    public void regenerate() {
        if (this.getCurrentHP() < 400 || this.getSpecialIsUsed() == false) {
            System.out.println(
                    ConsoleColors.GREEN_BOLD + getName() + " utilise la magie pour se soigner!" + ConsoleColors.RESET);
            this.setCurrentHP(400);
            this.setSpecialIsUsed(true);
        } else if (this.getSpecialIsUsed() == true) {
            System.out.println(ConsoleColors.YELLOW + getName() + " a déjà utilisé sa magie pour se soigner."
                    + ConsoleColors.RESET);
        }
    }
}
