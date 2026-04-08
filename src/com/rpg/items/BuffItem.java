package com.rpg.items;

import com.rpg.core.GameCharacter;
import com.rpg.core.GameItem;
import com.rpg.core.ConsoleUtils;

public abstract class BuffItem extends GameItem {

    private int buffAmount;
    private int debuffAmount;
    private boolean isInUse;

    public BuffItem(String name, String description, String message, int buffAmount, int debuffAmount) {
        super(name, description, message);
        this.buffAmount = buffAmount;
        this.debuffAmount = debuffAmount;
        this.isInUse = false;
    }

    public abstract void applyBuff(GameCharacter character, GameCharacter target);

    public abstract void applyDebuff(GameCharacter character);

    @Override
    public void use(GameCharacter user, GameCharacter target) {
        ConsoleUtils.slowPrint(this.getMessage());
        applyBuff(user, target);
        setInUse(true);
    }

    public int getBuffAmount() {
        return buffAmount;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public int getDebuffAmount() {
        return debuffAmount;
    }
}
