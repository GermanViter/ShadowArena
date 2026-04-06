package com.rpg.items;

import com.rpg.core.GameItem;

public abstract class HealingItem extends GameItem {

    private int healingAmount;

    public HealingItem(String name, String description, int healingAmount, String message) {
        super(name, description, message);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}
