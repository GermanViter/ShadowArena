package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Dragon extends Monster {

    public Dragon() {
        super("Dragon Ancien", 400, 400, new String[] {
                "crache un feu ancestral sur vous!",
                "vous frappe avec sa queue massive!",
                "rugit, créant une onde de choc!",
                "mord avec la force des montagnes!"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.PURPLE_BOLD + getName() + ConsoleColors.RESET + " " + ConsoleColors.RED
                + getRandomAttackMessage() + ConsoleColors.RESET);
        target.takeDamage(40);
    }
}
