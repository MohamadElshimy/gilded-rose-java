package com.gildedrose.strategies;

import com.gildedrose.Item;

public interface ItemUpdateStrategy {

    void updateItem(Item item);

    default void decreaseSellIn(Item item) {
        item.sellIn--;
    }

    default void increaseQuality(Item item, int amount) {
        item.quality = Math.min(50, item.quality + amount);
    }

    default void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(0, item.quality - amount);
    }
}
