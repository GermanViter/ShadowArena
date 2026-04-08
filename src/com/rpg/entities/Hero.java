package com.rpg.entities;

import com.rpg.core.GameCharacter;
import com.rpg.core.ConsoleUtils;
import com.rpg.core.ConsoleColors;

public abstract class Hero extends GameCharacter {

    private int damage;
    private int level = 1;
    private int experience = 0;

    public Hero(String name, int hp, int maxHp, int mana, int maxMana, int damage) {
        super(name, hp, maxHp, mana, maxMana);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {

        this.damage = damage;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level++;
        damage += 5;
        setMaxHp(getMaxHP() + 20);
        setCurrentHP(getMaxHP());
        setMaxMana(getMaxMana() + 10);
        setMana(getMaxMana());
        ConsoleUtils.slowPrint(ConsoleColors.GREEN_BOLD + "\n✨ FÉLICITATIONS ! Vous avez atteint le niveau " + level + " ! ✨" + ConsoleColors.RESET);
        ConsoleUtils.slowPrint(ConsoleColors.WHITE + "Vos statistiques ont augmenté !" + ConsoleColors.RESET);
    }

    public void gainExperience(int exp) {
        experience += exp;
        while (experience >= 150) {
            experience -= 150;
            levelUp();
        }
    }

    public void setHp(int hp) {
        setCurrentHP(hp);
    }

    public int getExperience() {
        return experience;
    }

}
