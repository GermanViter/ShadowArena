package com.rpg.entities;

import com.rpg.core.GameCharacter;

public abstract class Hero extends GameCharacter {
    public Hero(String name, int hp, int maxHp) {
        super(name, hp, maxHp);
    }
}
