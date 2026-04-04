package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleColors;

public class Dragon extends Monster {

    public Dragon() {
        super("Dragon Ancien", 300, 300, new String[]{
            "crache un feu ancestral sur vous!",
            "vous frappe avec sa queue massive!",
            "rugit, créant une onde de choc!",
            "mord avec la force des montagnes!"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(ConsoleColors.PURPLE_BOLD + getName() + ConsoleColors.RESET + " " + ConsoleColors.RED + getRandomAttackMessage() + ConsoleColors.RESET);
        target.takeDamage(40);
    }
}
