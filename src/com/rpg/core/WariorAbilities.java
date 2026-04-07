package com.rpg.core;

public interface WariorAbilities {
    void enableBerserkMode(GameCharacter target);
    void disableBerserkMode(GameCharacter target, int originalHP);
}
