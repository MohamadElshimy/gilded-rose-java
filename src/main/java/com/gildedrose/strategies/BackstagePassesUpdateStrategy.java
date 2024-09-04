package com.gildedrose.strategies;

import com.gildedrose.Item;

public class BackstagePassesUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);

        if (item.sellIn >= 10) {
            increaseQuality(item, 1);
        } else if (item.sellIn >= 5) {
            increaseQuality(item, 2);
        } else if (item.sellIn >= 0) {
            increaseQuality(item, 3);
        } else {
            item.quality = 0;
        }
    }
}
