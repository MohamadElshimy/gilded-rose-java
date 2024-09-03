package com.gildedrose;

class GildedRose {
    Item[] items;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals(SULFURAS)) {
                return;
            }

            decreaseSellIn(item);

            switch (item.name) {
                case AGED_BRIE:
                    updateAgedBrie(item);
                    break;
                case BACKSTAGE_PASSES:
                    updateBackstagePasses(item);
                    break;
                default:
                    updateNormalItem(item);
                    break;
            }

            if (item.sellIn < 0) {
                handleExpiredItem(item);
            }
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item);
    }

    private void updateBackstagePasses(Item item) {
        increaseQuality(item);

        if (item.sellIn <= 10) {
            increaseQuality(item);
        }
        if (item.sellIn <= 5) {
            increaseQuality(item);
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item);
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    private void handleExpiredItem(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            increaseQuality(item); // made this to increase by two as opposite to normal items (not 100% sure)
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            item.quality = 0;
        } else {
            decreaseQuality(item); // double quality degradation
        }
    }
}