package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Cerberus extends Monster {

    public Cerberus() {
        super("Cerbère", 160, 160, new String[]{
            "vous mord simultanément avec ses trois têtes !",
            "crache des flammes infernales sur vous !",
            "vous lacère avec ses griffes de fer !",
            "aboie si fort que le sol tremble sous vos pieds !"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.PURPLE_BOLD + getName() + ConsoleColors.RESET + " " + ConsoleColors.RED + getRandomAttackMessage() + ConsoleColors.RESET);
        target.takeDamage(28);
    }
}
