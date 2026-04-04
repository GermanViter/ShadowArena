package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.Regenerable;

public class Mage extends Hero implements Regenerable {

    public Mage(String name) {
        super(name, 100, 100);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " lance une boule de feu sur " + target.getName() + "!");
        target.takeDamage(25);
    }

    @Override
    public void regenerate() {
        if (this.getCurrentHP() < 100) {
            System.out.println(getName() + " utilise la magie pour se soigner!");
            this.setCurrentHP(100);

        }
    }
}
