package com.gildedrose.strategies;

import com.gildedrose.Item;

public class ConjuredUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);

        if (item.sellIn >= 0) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 4);
        }
    }
}
