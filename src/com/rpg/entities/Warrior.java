package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.HeavyAttack;

public class Warrior extends Hero implements HeavyAttack {

    public Warrior(String name) {
        super(name, 450, 450, false);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " abat une épée puissante sur " + target.getName() + "!");
        target.takeDamage(20);
    }

    @Override
    public void heavyAttack(GameCharacter target) {
        if (this.getSpecialIsUsed() == false) {

            System.out.println(getName() + " fait une attaque lourde sur " + target.getName() + "!");
            target.takeDamage(30);
            this.setSpecialIsUsed(true);
        } else {
            System.out.println(getName() + " a deja utiliser son attaque lourde");
        }
    }
}
