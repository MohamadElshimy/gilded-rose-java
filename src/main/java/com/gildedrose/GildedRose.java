package com.gildedrose;

import com.gildedrose.strategies.*;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;
    private final Map<String, ItemUpdateStrategy> strategyMap;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured";

    public GildedRose(Item[] items) {
        this.items = items;

        strategyMap = new HashMap<>();
        strategyMap.put(AGED_BRIE, new AgedBrieUpdateStrategy());
        strategyMap.put(SULFURAS, new SulfurasUpdateStrategy());
        strategyMap.put(BACKSTAGE_PASSES, new BackstagePassesUpdateStrategy());
        strategyMap.put(CONJURED, new ConjuredUpdateStrategy());
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdateStrategy strategy = getStrategyForItem(item.name);
            strategy.updateItem(item);
        }
    }

    private ItemUpdateStrategy getStrategyForItem(String itemName) {
        ItemUpdateStrategy specialStrategy = strategyMap.get(itemName);
        return specialStrategy != null ? specialStrategy : new NormalItemUpdateStrategy();
    }
}