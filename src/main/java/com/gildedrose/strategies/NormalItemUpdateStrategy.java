package com.gildedrose.strategies;

import com.gildedrose.Item;

public class NormalItemUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);

        if (item.sellIn >= 0) {
            decreaseQuality(item, 1);
        } else {
            decreaseQuality(item, 2);
        }
    }
}
