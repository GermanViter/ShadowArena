package com.rpg.core;

public interface BerserkerAbilities {
    void enableBerserkMode(GameCharacter target);
    void disableBerserkMode(GameCharacter target, int originalHP);
}
