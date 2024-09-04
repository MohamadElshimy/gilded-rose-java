package com.gildedrose.items.immutableitems;

import com.gildedrose.Item;

public abstract class ImmutableItem extends Item {

    public ImmutableItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
}
