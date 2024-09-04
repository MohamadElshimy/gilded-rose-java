package com.gildedrose;

import com.gildedrose.items.updatableitems.UpdatableItem;

class GildedRoseAnotherSolution {
    Item[] items;

    public GildedRoseAnotherSolution(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if(item instanceof UpdatableItem) {
                ((UpdatableItem)item).update();
            }
        }
    }
}