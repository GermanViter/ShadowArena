package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;

public class Amulette extends BuffItem {

    public Amulette() {
        super("Amulette", "Une amulette ancienne qui augmente votre force mais vous ronge la vie.",
                "Vous enfilez l'amulette. Une puissance sombre coule en vous, mais vous sentez votre vitalité s'échapper...", 40, 10);
    }

    @Override
    public void applyBuff(GameCharacter user, GameCharacter target) {
        user.setAmuletteActive(true);
    }

    @Override
    public void applyDebuff(GameCharacter user) {
        // Le debuff est géré par tour dans Main.java
    }
}
