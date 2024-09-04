package com.gildedrose.items.updatableitems;

public class Conjured extends UpdatableItem {

    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        decreaseSellIn();

        if (sellIn >= 0) {
            decreaseQuality(2);
        } else {
            decreaseQuality(4);
        }
    }
}
