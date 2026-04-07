package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;

public class ManaPotion extends HealingItem {

    public ManaPotion() {
        super("potion de mana", "Une potion qui restaure une partie de votre mana.", 50,
                "Vous buvez la potion de mana et sentez votre énergie magique augmenter !");
    }

    @Override
    public void use(GameCharacter user, GameCharacter target) {
        int newMana = user.getMana() + getHealingAmount();
        if (newMana > user.getMaxMana()) {
            newMana = user.getMaxMana();
        }
        user.setMana(newMana);
        ConsoleUtils.slowPrint(getMessage());
    }
}
