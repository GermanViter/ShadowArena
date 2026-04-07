package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;

public class Amulette extends BuffItem {
    public Amulette() {
        super("Amulette", "An ancient amulet that boosts your strength.",
                "You feel a surge of power as you wear the amulet!", 50, 10);
    }

    @Override
    public void applyBuff(GameCharacter character, GameCharacter target) {
        target.takeDamage(getBuffAmount());
    }

    @Override
    public void applyDebuff(GameCharacter character) {
        character.takeDamage(getDebuffAmount());
    }
}
