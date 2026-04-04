package com.rpg.core;

public abstract class GameCharacter {

    private String name;
    private int currentHP;
    private final int MAX_HP;

    public GameCharacter(String name, int currentHP, int maxHP) {
        this.name = name;
        this.currentHP = currentHP;
        this.MAX_HP = maxHP;
    }

    public abstract void attack(GameCharacter target);

    public void takeDamage(int amount) {
        this.currentHP -= amount;
        if (this.currentHP < 0)
            this.currentHP = 0;
        ConsoleUtils.slowPrint(ConsoleColors.RED + this.name + " a pris " + amount + " dégâts! " + ConsoleColors.RESET + 
                           "(HP: " + ConsoleColors.GREEN + this.currentHP + ConsoleColors.RESET + "/" + this.MAX_HP + ")");
    }

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int hp) {
        this.currentHP = hp;
    }

    public int getMaxHP() {
        return MAX_HP;
    }

    public boolean isAlive() {
        return this.currentHP > 0;
    }
}
