package com.rpg.entities;

import com.rpg.core.GameCharacter;

public class Dragon extends Monster {

    public Dragon() {
        super("Elder Dragon", 300, 300, new String[]{
            "breathes ancient fire on you!",
            "slams its massive tail into you!",
            "roars, creating a shockwave!",
            "bites down with the strength of mountains!"
        });
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " " + getRandomAttackMessage());
        target.takeDamage(40);
    }
}
