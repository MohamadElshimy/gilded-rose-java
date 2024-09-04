package com.gildedrose.items.updatableitems;

public class BackstagePasses extends UpdatableItem {

    public BackstagePasses(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        decreaseSellIn();

        if (sellIn >= 10) {
            increaseQuality(1);
        } else if (sellIn >= 5) {
            increaseQuality(2);
        } else if (sellIn >= 0) {
            increaseQuality(3);
        } else {
            quality = 0;
        }
    }
}
