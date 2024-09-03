package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testLoweringBothQualityAndSellIn() {
        Item item1 = new Item("justAnItem", 15, 45);
        Item item2 = new Item("justAnotherItem", 10, 33);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(44, app.items[0].quality);
        assertEquals(9, app.items[1].sellIn);
        assertEquals(32, app.items[1].quality);
    }

    @Test
    void testDegradingTwiceAsFast() {
        Item item1 = new Item("justAnItem", 0, 10);
        Item item2 = new Item("justAnotherItem", 2, 13);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
        assertEquals(1, app.items[1].sellIn);
        assertEquals(12, app.items[1].quality);
    }

    @Test
    void testQualityIsNeverNegative() {
        Item item1 = new Item("justAnItem", 2, 0);
        Item item2 = new Item("justAnotherItem", 0, 0);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    void testAgedBrieQualityIncreases() {
        Item item1 = new Item("justAnItem", 10, 38);
        Item item2 = new Item("Aged Brie", 10, 38);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(37, app.items[0].quality);
        assertEquals(9, app.items[1].sellIn);
        assertEquals(39, app.items[1].quality);
    }

    @Test
    void testQualityOfItemNeverExceedsLimit() {
        Item item1 = new Item("Aged Brie", 4, 45);
        Item item2 = new Item("Aged Brie", 2, 50);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(46, app.items[0].quality);
        assertEquals(1, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    void testSulfurasItem() {
        Item item1 = new Item("justAnItem", 4, 25);
        Item item2 = new Item("Sulfuras, Hand of Ragnaros", 2, 45);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(24, app.items[0].quality);
        assertEquals(2, app.items[1].sellIn);
        assertEquals(45, app.items[1].quality);
    }

    @Test
    void testBackstagePassesQualityIncreases() {
        Item item1 = new Item("justAnItem", 10, 38);
        Item item2 = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 38);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(37, app.items[0].quality);
        assertEquals(10, app.items[1].sellIn);
        assertEquals(39, app.items[1].quality);
    }

    @Test
    void testBackstagePassesDaysCases() {
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 38);
        Item item2 = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 38);
        Item item3 = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 38);
        Item item4 = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 38);
        Item[] items = new Item[] { item1, item2, item3, item4 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(39, app.items[0].quality);
        assertEquals(8, app.items[1].sellIn);
        assertEquals(40, app.items[1].quality);
        assertEquals(3, app.items[2].sellIn);
        assertEquals(41, app.items[2].quality);
        assertEquals(-1, app.items[3].sellIn);
        assertEquals(0, app.items[3].quality);
    }






}
