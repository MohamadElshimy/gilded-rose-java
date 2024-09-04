package com.gildedrose.items.updatableitems;

import com.gildedrose.Item;

public abstract class UpdatableItem extends Item {

    public UpdatableItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void update();

    protected void decreaseSellIn() {
        sellIn--;
    }

    protected void increaseQuality(int amount) {
        quality = Math.min(50, quality + amount);
    }

    protected void decreaseQuality(int amount) {
        quality = Math.max(0, quality - amount);
    }
}
