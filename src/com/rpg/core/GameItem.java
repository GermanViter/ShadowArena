package com.rpg.core;

public abstract class GameItem {

    private String name;
    private String description;

    public GameItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use(GameCharacter user);
}
