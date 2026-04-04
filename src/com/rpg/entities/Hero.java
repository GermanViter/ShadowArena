package com.rpg.entities;

import com.rpg.core.GameCharacter;

public abstract class Hero extends GameCharacter {
    private boolean specialIsUsed;

    public Hero(String name, int hp, int maxHp, boolean spe) {
        super(name, hp, maxHp);
        this.specialIsUsed = spe;
    }

    public void setSpecialIsUsed(boolean status) {
        this.specialIsUsed = status;
    }

    public boolean getSpecialIsUsed() {
        return this.specialIsUsed;
    }
}
