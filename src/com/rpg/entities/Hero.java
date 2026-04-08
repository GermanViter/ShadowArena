package com.rpg.entities;

import com.rpg.core.GameCharacter;

public abstract class Hero extends GameCharacter {

    private int damage;

    public Hero(String name, int hp, int maxHp, int mana, int maxMana, int damage) {
        super(name, hp, maxHp, mana, maxMana);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
