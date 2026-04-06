package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;

public class Grenade extends AttackItem {

    public Grenade() {
        super("grenade", "Une grenade qui inflige des dégâts de zone.", 150,
                "Vous lancez une grenade et infligez 150 points de dégâts !");
    }

    @Override
    public void use(GameCharacter user, GameCharacter target) {
        target.takeDamage(this.getDamage());
        ConsoleUtils.slowPrint(getMessage());
    }
}
