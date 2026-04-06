package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.Regenerable;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Berserker extends Hero {

    public Berserker(String name) {
        super(name, 500, 500, 300, 100);
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.RED_BOLD + getName() + ConsoleColors.RESET
                + " charge furieusement sur " + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");

        int damageAmount = 40 + (getMaxHP() - getCurrentHP());
        target.takeDamage(damageAmount);
    }
}
