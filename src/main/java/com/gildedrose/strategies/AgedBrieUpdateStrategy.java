package com.gildedrose.strategies;

import com.gildedrose.Item;

public class AgedBrieUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);

        if (item.sellIn >= 0) {
            increaseQuality(item, 1);
        } else {
            increaseQuality(item, 2);
        }
    }
}
