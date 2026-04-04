package com.rpg.entities;

import com.rpg.core.GameCharacter;

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
        System.out.println(getName() + " " + getRandomAttackMessage());
        target.takeDamage(40);
    }
}
