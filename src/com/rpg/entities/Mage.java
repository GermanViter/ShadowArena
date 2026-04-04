package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.Regenerable;

public class Mage extends Hero implements Regenerable {

    public Mage(String name) {
        super(name, 400, 100, false);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " lance une boule de feu sur " + target.getName() + "!");
        target.takeDamage(25);
    }

    @Override
    public void regenerate() {
        if (this.getCurrentHP() < 400 || this.getSpecialIsUsed() == false) {
            System.out.println(getName() + " utilise la magie pour se soigner!");
            this.setCurrentHP(400);
            this.setSpecialIsUsed(true);
        } else if (this.getSpecialIsUsed() == true) {
            System.out.println(getName() + " a deja utilise sa magie pour se soigner");
        }
    }
}
