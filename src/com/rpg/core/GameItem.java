package com.rpg.core;

public abstract class GameItem {

    private String name;
    private String description;
    private String message;

    public GameItem(String name, String description, String message) {
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public abstract void use(GameCharacter user, GameCharacter target);
}
