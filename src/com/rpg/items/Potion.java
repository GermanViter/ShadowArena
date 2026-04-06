package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;

public class Potion extends HealingItem {

    public Potion() {
        super("potion de soin", "Une potion qui restaure une partie de la santé.", 80,
                "Vous buvez une potion de soin et récupérez 80 HP !");
    }

    @Override
    public void use(GameCharacter user, GameCharacter target) {
        int newHP = user.getCurrentHP() + getHealingAmount();
        if (newHP > user.getMaxHP()) {
            newHP = user.getMaxHP();
        }
        user.setCurrentHP(newHP);
        ConsoleUtils.slowPrint(getMessage());
    }
}
