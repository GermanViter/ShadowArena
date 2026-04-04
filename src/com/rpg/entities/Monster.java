package com.rpg.entities;

import com.rpg.core.GameCharacter;
import java.util.Random;

public abstract class Monster extends GameCharacter {
    private boolean isDead;
    private String[] attackMessages;

    public Monster(String name, int hp, int maxHp, String[] messages) {
        super(name, hp, maxHp);
        this.attackMessages = messages;
        this.isDead = false;
    }

    public String getRandomAttackMessage() {
        Random rand = new Random();
        return attackMessages[rand.nextInt(attackMessages.length)];
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }
}
