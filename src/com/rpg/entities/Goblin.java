package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Goblin extends Monster {

    public Goblin() {
        super("Gobelin Sournois", 50, 50, new String[]{
            "vous mord avec des dents sales!",
            "vous griffe le visage!",
            "vous lance une pierre tranchante!",
            "essaie de voler votre déjeuner mais trébuche et vous frappe!"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.PURPLE_BOLD + getName() + ConsoleColors.RESET + " " + ConsoleColors.RED + getRandomAttackMessage() + ConsoleColors.RESET);
        target.takeDamage(10);
    }
}
