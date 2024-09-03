package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    public static final String RANDOM_ITEM = "just an item";
    public static final String SECOND_RANDOM_ITEM = "just another item";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void testUpdateQualityDecreasingBothQualityAndSellInForNormalItems() {
        Item item1 = new Item(RANDOM_ITEM, 15, 45);
        Item item2 = new Item(SECOND_RANDOM_ITEM, 1, 33);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn, "SellIn did not decrease as expected for normal items");
        assertEquals(44, app.items[0].quality, "Quality did not decrease as expected for normal items");
        assertEquals(0, app.items[1].sellIn, "SellIn did not decrease as expected for normal items");
        assertEquals(32, app.items[1].quality, "Quality did not decrease as expected for normal items");
    }

    @Test
    void testUpdateQualityDegradingTwiceAsFastForPassedSellInDates() {
        Item item1 = new Item(RANDOM_ITEM, 0, 10);
        Item item2 = new Item(SECOND_RANDOM_ITEM, 2, 13);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn, "SellIn did not decrease correctly after sell-in date passed");
        assertEquals(8, app.items[0].quality, "Quality did not decrease correctly after sell-in date");
        assertEquals(1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(12, app.items[1].quality, "Quality did not decrease as expected");
    }

    @Test
    void testUpdateQualityThatQualityIsNeverNegative() {
        Item item1 = new Item(RANDOM_ITEM, 2, 0);
        Item item2 = new Item(SECOND_RANDOM_ITEM, 0, 0);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(0, app.items[0].quality, "Quality should not be negative");
        assertEquals(-1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(0, app.items[1].quality, "Quality should not be negative");
    }

    @Test
    void testUpdateQualityAgedBrieQualityIncreases() {
        Item item1 = new Item(RANDOM_ITEM, 10, 38);
        Item item2 = new Item(AGED_BRIE, 10, 38);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(37, app.items[0].quality, "Quality did not decrease as expected");
        assertEquals(9, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(39, app.items[1].quality, "Quality of Aged Brie did not increase as expected");
    }

    @Test
    void testUpdateQualityThatQualityOfItemNeverExceedsLimit() {
        Item item1 = new Item(AGED_BRIE, 4, 45);
        Item item2 = new Item(AGED_BRIE, 2, 50);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(46, app.items[0].quality, "Quality did not increase correctly");
        assertEquals(1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(50, app.items[1].quality, "Quality should not exceed 50");
    }

    @Test
    void testUpdateQualitySulfurasItemLogic() {
        Item item1 = new Item(RANDOM_ITEM, 4, 25);
        Item item2 = new Item(SULFURAS, 2, 45);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(24, app.items[0].quality, "Quality did not decrease as expected");
        assertEquals(2, app.items[1].sellIn, "SellIn for Sulfuras should not change");
        assertEquals(45, app.items[1].quality, "Quality for Sulfuras should not change");
    }

    @Test
    void testUpdateQualityBackstagePassesQualityIncreases() {
        Item item1 = new Item(RANDOM_ITEM, 10, 38);
        Item item2 = new Item(BACKSTAGE_PASSES, 11, 38);
        Item[] items = new Item[] { item1, item2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(37, app.items[0].quality, "Quality did not decrease as expected");
        assertEquals(10, app.items[1].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(39, app.items[1].quality, "Quality did not increase as expected for Backstage Passes");
    }

    @Test
    void testUpdateQualityBackstagePassesDifferentDaysCases() {
        Item item1 = new Item(BACKSTAGE_PASSES, 11, 38);
        Item item2 = new Item(BACKSTAGE_PASSES, 10, 38);
        Item item3 = new Item(BACKSTAGE_PASSES, 5, 38);
        Item item4 = new Item(BACKSTAGE_PASSES, 0, 38);
        Item[] items = new Item[] { item1, item2, item3, item4 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(39, app.items[0].quality, "Quality did not increase as expected for Backstage Passes");
        assertEquals(9, app.items[1].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(40, app.items[1].quality, "Quality did not increase by 2 when sellIn is 10 or less");
        assertEquals(4, app.items[2].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(41, app.items[2].quality, "Quality did not increase by 3 when sellIn is 5 or less");
        assertEquals(-1, app.items[3].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(0, app.items[3].quality, "Quality should be zero when sellIn is 0 or less for Backstage Passes");
    }






}
