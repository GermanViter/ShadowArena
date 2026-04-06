package src.com.rpg.items;

import src.com.rpg.core.GameItem;

public abstract class AttackItem extends GameItem {

    private int damage;

    public AttackItem(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
