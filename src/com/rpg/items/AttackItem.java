package com.rpg.items;

import com.rpg.core.GameItem;

public abstract class AttackItem extends GameItem {

    private int damage;

    public AttackItem(String name, String description, int damage, String message) {
        super(name, description, message);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
