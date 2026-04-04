package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.HeavyAttack;

public class Warrior extends Hero implements HeavyAttack {

    public Warrior(String name) {
        super(name, 150, 150);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " abat une épée puissante sur " + target.getName() + "!");
        target.takeDamage(20);
    }

    @Override
    public void heavyAttack(GameCharacter target) {
        System.out.println(getName() + " fait une attaque lourde sur " + target.getName() + "!");
        target.takeDamage(30);
    }
}
