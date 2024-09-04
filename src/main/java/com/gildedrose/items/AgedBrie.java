package com.gildedrose.items;

public class AgedBrie extends UpdatableItem {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        decreaseSellIn();

        if (sellIn >= 0) {
            increaseQuality(1);
        } else {
            increaseQuality(2);
        }
    }
}
