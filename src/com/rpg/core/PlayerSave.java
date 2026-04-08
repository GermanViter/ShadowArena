package com.rpg.core;

import java.util.ArrayList;
import com.rpg.entities.Hero;

public class PlayerSave {
    private Hero hero;
    private int defeatedMonsters;
    private ArrayList<GameItem> inventory;
    private boolean isAmuletteActive;
    private boolean lowHealthPotionGiven;
    private boolean lowManaPotionGiven;
    private boolean amuletteGiven;

    public PlayerSave(Hero hero, int defeatedMonsters, ArrayList<GameItem> inventory,
            boolean isAmuletteActive, boolean lowHealthPotionGiven, boolean lowManaPotionGiven, boolean amuletteGiven) {
        this.hero = hero;
        this.defeatedMonsters = defeatedMonsters;
        this.inventory = inventory;
        this.isAmuletteActive = isAmuletteActive;
        this.lowHealthPotionGiven = lowHealthPotionGiven;
        this.lowManaPotionGiven = lowManaPotionGiven;
        this.amuletteGiven = amuletteGiven;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getDefeatedMonsters() {
        return defeatedMonsters;
    }

    public void setDefeatedMonsters(int defeatedMonsters) {
        this.defeatedMonsters = defeatedMonsters;
    }

    public ArrayList<GameItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<GameItem> inventory) {
        this.inventory = inventory;
    }

    public boolean isAmuletteActive() {
        return isAmuletteActive;
    }

    public void setAmuletteActive(boolean amuletteActive) {
        isAmuletteActive = amuletteActive;
    }

    public boolean isLowHealthPotionGiven() {
        return lowHealthPotionGiven;
    }

    public void setLowHealthPotionGiven(boolean lowHealthPotionGiven) {
        this.lowHealthPotionGiven = lowHealthPotionGiven;
    }

    public boolean isLowManaPotionGiven() {
        return lowManaPotionGiven;
    }

    public void setLowManaPotionGiven(boolean lowManaPotionGiven) {
        this.lowManaPotionGiven = lowManaPotionGiven;
    }

    public boolean isAmuletteGiven() {
        return amuletteGiven;
    }

    public void setAmuletteGiven(boolean amuletteGive) {
        this.amuletteGiven = amuletteGive;
    }
}
