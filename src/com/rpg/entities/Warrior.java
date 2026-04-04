package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.HeavyAttack;
import com.rpg.core.ConsoleColors;

public class Warrior extends Hero implements HeavyAttack {

    public Warrior(String name) {
        super(name, 450, 450, false);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(ConsoleColors.BLUE_BOLD + getName() + ConsoleColors.RESET + " abat une épée puissante sur " + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");
        target.takeDamage(20);
    }

    @Override
    public void heavyAttack(GameCharacter target) {
        if (this.getSpecialIsUsed() == false) {

            System.out.println(ConsoleColors.BLUE_BOLD + getName() + ConsoleColors.RED_BOLD + " FAIT UNE ATTAQUE LOURDE SUR " + ConsoleColors.PURPLE + target.getName() + ConsoleColors.RESET + "!");
            target.takeDamage(30);
            this.setSpecialIsUsed(true);
        } else {
            System.out.println(ConsoleColors.YELLOW + getName() + " a déjà utilisé son attaque lourde." + ConsoleColors.RESET);
        }
    }
}
