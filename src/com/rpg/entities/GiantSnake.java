package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleColors;
import com.rpg.core.ConsoleUtils;

public class GiantSnake extends Monster {

    public GiantSnake() {
        super("Serpent Géant", 380, 380, new String[] {
                "siffle et vous mord avec ses crochets venimeux !",
                "s'enroule autour de vous pour vous étouffer !",
                "vous frappe avec sa queue puissante !",
                "projette du venin aveuglant dans vos yeux !"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        ConsoleUtils.slowPrint(ConsoleColors.PURPLE_BOLD + getName() + ConsoleColors.RESET + " " + ConsoleColors.RED
                + getRandomAttackMessage() + ConsoleColors.RESET);
        target.takeDamage(12);
    }
}
