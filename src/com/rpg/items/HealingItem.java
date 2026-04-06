package src.com.rpg.items;

import src.com.rpg.core.GameItem;

public abstract class HealingItem extends GameItem {

    private int healingAmount;

    public HealingItem(String name, String description, int healingAmount) {
        super(name, description);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}
