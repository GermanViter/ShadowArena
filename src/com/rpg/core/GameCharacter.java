package com.rpg.core;

public abstract class GameCharacter {

    private String name;
    private int currentHP;
    private final int MAX_HP;
    private boolean isDefending;

    public GameCharacter(String name, int currentHP, int maxHP) {
        this.name = name;
        this.currentHP = currentHP;
        this.MAX_HP = maxHP;
        this.isDefending = false;
    }

    public abstract void attack(GameCharacter target);

    public void takeDamage(int amount) {
        if (this.isDefending) {
            amount = 0; // Aucun dégât reçu !
            ConsoleUtils.slowPrint(
                    ConsoleColors.BLUE + this.name + " bloque parfaitement l'attaque et ne reçoit aucun dégât !" + ConsoleColors.RESET);
            this.isDefending = false;
        }
        this.currentHP -= amount;
        if (this.currentHP < 0)
            this.currentHP = 0;
        ConsoleUtils.slowPrint(ConsoleColors.RED + this.name + " a pris " + amount + " dégâts! " + ConsoleColors.RESET +
                "(HP: " + ConsoleColors.GREEN + this.currentHP + ConsoleColors.RESET + "/" + this.MAX_HP + ")");
    }

    public void defend() {
        this.isDefending = true;
        ConsoleUtils.slowPrint(
                ConsoleColors.BLUE_BOLD + this.name + " se met en position de défense !" + ConsoleColors.RESET);
    }

    public boolean getIsDefending() {
        return this.isDefending;
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
