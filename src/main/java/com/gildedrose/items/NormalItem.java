package com.gildedrose.items;

public class NormalItem extends UpdatableItem {

    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        decreaseSellIn();

        if (sellIn >= 0) {
            decreaseQuality(1);
        } else {
            decreaseQuality(2);
        }
    }
}
