package com.rpg.entities;

import com.rpg.core.GameCharacter;
import java.util.Random;

public abstract class Monster extends GameCharacter {
    
    private String[] attackMessages;

    public Monster(String name, int hp, int maxHp, String[] messages) {
        super(name, hp, maxHp);
        this.attackMessages = messages;
    }

    public String getRandomAttackMessage() {
        Random rand = new Random();
        return attackMessages[rand.nextInt(attackMessages.length)];
    }
}
