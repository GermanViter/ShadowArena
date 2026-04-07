package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;

public class ManaPotion extends HealingItem {

    public ManaPotion() {
        super("Mana Potion", "Restores 50 mana points.", 50,
                "You drink the mana potion and feel your magical energy surge!");
    }

    @Override
    public void use(GameCharacter character, GameCharacter target) {
        ConsoleUtils.slowPrint(getMessage());
        character.setMana(character.getMana() + getHealingAmount());
    }
}
