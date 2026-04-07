package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class Troll extends Monster {

    public Troll() {
        super("Troll des Montagnes", 300, 300, new String[] {
                "vous assène un coup de massue colossal !",
                "tente de vous écraser avec son pied géant !",
                "pousse un rugissement fétide qui vous désoriente !",
                "vous jette un rocher énorme !"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.PURPLE_BOLD + getName() + ConsoleColors.RESET + " " + ConsoleColors.RED
                + getRandomAttackMessage() + ConsoleColors.RESET);
        target.takeDamage(15);
    }
}
