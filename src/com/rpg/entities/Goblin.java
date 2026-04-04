package com.rpg.entities;

import com.rpg.core.GameCharacter;

public class Goblin extends Monster {

    public Goblin() {
        super("Sneaky Goblin", 50, 50, new String[]{
            "bites you with dirty teeth!",
            "scratches your face!",
            "throws a sharp rock at you!",
            "tries to steal your lunch but trips and hits you!"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " " + getRandomAttackMessage());
        target.takeDamage(10);
    }
}
