package com.rpg.entities;

import com.rpg.core.GameCharacter;

public abstract class Monster extends GameCharacter {
    public Monster(String name, int hp, int maxHp) {
        super(name, hp, maxHp);
    }
}
