package com.gildedrose;

import com.gildedrose.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseAnotherSolutionTest {

    public static final String RANDOM_ITEM = "just an item";
    public static final String SECOND_RANDOM_ITEM = "just another item";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured";

    @Test
    void testUpdateQualityDecreasingBothQualityAndSellInForNormalItems() {
        Item item1 = new NormalItem(RANDOM_ITEM, 15, 45);
        Item item2 = new NormalItem(SECOND_RANDOM_ITEM, 1, 33);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(14, app.items[0].sellIn, "SellIn did not decrease as expected for normal items");
        assertEquals(44, app.items[0].quality, "Quality did not decrease as expected for normal items");
        assertEquals(0, app.items[1].sellIn, "SellIn did not decrease as expected for normal items");
        assertEquals(32, app.items[1].quality, "Quality did not decrease as expected for normal items");
    }

    @Test
    void testUpdateQualityDegradingTwiceAsFastForPassedSellInDates() {
        Item item1 = new NormalItem(RANDOM_ITEM, 0, 10);
        Item item2 = new NormalItem(SECOND_RANDOM_ITEM, 2, 13);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn, "SellIn did not decrease correctly after sell-in date passed");
        assertEquals(8, app.items[0].quality, "Quality did not decrease correctly after sell-in date");
        assertEquals(1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(12, app.items[1].quality, "Quality did not decrease as expected");
    }

    @Test
    void testUpdateQualityThatQualityIsNeverNegative() {
        Item item1 = new NormalItem(RANDOM_ITEM, 2, 0);
        Item item2 = new NormalItem(SECOND_RANDOM_ITEM, 0, 0);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(1, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(0, app.items[0].quality, "Quality should not be negative");
        assertEquals(-1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(0, app.items[1].quality, "Quality should not be negative");
    }

    @Test
    void testUpdateQualityAgedBrieQualityIncreases() {
        Item item1 = new NormalItem(RANDOM_ITEM, 10, 38);
        Item item2 = new AgedBrie(AGED_BRIE, 10, 38);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(9, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(37, app.items[0].quality, "Quality did not decrease as expected");
        assertEquals(9, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(39, app.items[1].quality, "Quality of Aged Brie did not increase as expected");
    }

    @Test
    void testUpdateQualityAgedBrieEdgeCases() {
        Item item1 = new AgedBrie(AGED_BRIE, 2, 50);
        Item item2 = new AgedBrie(AGED_BRIE, 10, 38);
        Item item3 = new AgedBrie(AGED_BRIE, 0, 47);
        Item[] items = new Item[] { item1, item2, item3 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(1, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(50, app.items[0].quality, "Quality of Aged Brie should not exceed 50");
        assertEquals(9, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(39, app.items[1].quality, "Quality of Aged Brie did not increase as expected");
        assertEquals(-1, app.items[2].sellIn, "SellIn did not decrease as expected");
        assertEquals(49, app.items[2].quality, "Quality of Aged Brie did not increase as expected");
    }

    @Test
    void testUpdateQualityThatQualityOfItemNeverExceedsLimit() {
        Item item1 = new AgedBrie(AGED_BRIE, 4, 45);
        Item item2 = new AgedBrie(AGED_BRIE, 2, 50);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(3, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(46, app.items[0].quality, "Quality did not increase correctly");
        assertEquals(1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(50, app.items[1].quality, "Quality should not exceed 50");
    }

    @Test
    void testUpdateQualitySulfurasItemLogic() {
        Item item1 = new NormalItem(RANDOM_ITEM, 4, 25);
        Item item2 = new Sulfuras(SULFURAS, 2, 80);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(3, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(24, app.items[0].quality, "Quality did not decrease as expected");
        assertEquals(2, app.items[1].sellIn, "SellIn for Sulfuras should not change");
        assertEquals(80, app.items[1].quality, "Quality for Sulfuras should not change");
    }

    @Test
    void testUpdateQualityBackstagePassesQualityIncreases() {
        Item item1 = new NormalItem(RANDOM_ITEM, 10, 38);
        Item item2 = new BackstagePasses(BACKSTAGE_PASSES, 11, 38);
        Item[] items = new Item[] { item1, item2 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(9, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(37, app.items[0].quality, "Quality did not decrease as expected");
        assertEquals(10, app.items[1].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(39, app.items[1].quality, "Quality did not increase as expected for Backstage Passes");
    }

    @Test
    void testUpdateQualityBackstagePassesDifferentDaysCases() {
        Item item1 = new BackstagePasses(BACKSTAGE_PASSES, 11, 38);
        Item item2 = new BackstagePasses(BACKSTAGE_PASSES, 10, 38);
        Item item3 = new BackstagePasses(BACKSTAGE_PASSES, 5, 38);
        Item item4 = new BackstagePasses(BACKSTAGE_PASSES, 0, 38);
        Item item5 = new BackstagePasses(BACKSTAGE_PASSES, 5, 50);
        Item[] items = new Item[] { item1, item2, item3, item4, item5 };

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(10, app.items[0].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(39, app.items[0].quality, "Quality did not increase as expected for Backstage Passes");
        assertEquals(9, app.items[1].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(40, app.items[1].quality, "Quality did not increase by 2 when sellIn is 10 or less");
        assertEquals(4, app.items[2].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(41, app.items[2].quality, "Quality did not increase by 3 when sellIn is 5 or less");
        assertEquals(-1, app.items[3].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(0, app.items[3].quality, "Quality should be zero when sellIn is 0 or less for Backstage Passes");
        assertEquals(4, app.items[4].sellIn, "SellIn did not decrease as expected for Backstage Passes");
        assertEquals(50, app.items[4].quality, "Quality should not exceed 50 for for Backstage Passes");
    }

    @Test
    void testUpdateQualityComprehensiveWithMixedItems() {
        Item item1 = new NormalItem(RANDOM_ITEM, 15, 20);
        Item item2 = new NormalItem(RANDOM_ITEM, 0, 6);
        Item item3 = new AgedBrie(AGED_BRIE, 10, 48);
        Item item4 = new Sulfuras(SULFURAS, 0, 80);
        Item item5 = new BackstagePasses(BACKSTAGE_PASSES, 5, 45);
        Item[] items = new Item[] { item1, item2, item3, item4, item5};

        GildedRoseAnotherSolution app = new GildedRoseAnotherSolution(items);
        app.updateQuality();

        assertEquals(14, item1.sellIn, "SellIn did not decrease correctly for normal item");
        assertEquals(19, item1.quality, "Quality did not decrease correctly for normal item");
        assertEquals(-1, item2.sellIn, "SellIn did not decrease correctly for item past sell-in date");
        assertEquals(4, item2.quality, "Quality did not degrade twice as fast after sell-in date");
        assertEquals(9, item3.sellIn, "SellIn did not decrease correctly for Aged Brie");
        assertEquals(49, item3.quality, "Quality did not increase correctly for Aged Brie");
        assertEquals(0, item4.sellIn, "SellIn should not change for Sulfuras");
        assertEquals(80, item4.quality, "Quality should not change for Sulfuras");
        assertEquals(4, item5.sellIn, "SellIn did not decrease correctly for Backstage Passes");
        assertEquals(48, item5.quality, "Quality did not increase correctly for Backstage Passes");
    }

    @Test
    void testUpdateQualityDecreasingBothQualityAndSellInForConjuredItems() {
        Item item1 = new Conjured(CONJURED, 15, 45);
        Item item2 = new Conjured(CONJURED, 1, 33);
        Item[] items = new Item[] { item1, item2 };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(14, app.items[0].sellIn, "SellIn did not decrease as expected for Conjured items");
        assertEquals(43, app.items[0].quality, "Quality did not decrease as expected for Conjured items");
        assertEquals(0, app.items[1].sellIn, "SellIn did not decrease as expected for Conjured items");
        assertEquals(31, app.items[1].quality, "Quality did not decrease as expected for Conjured items");
    }

    @Test
    void testUpdateQualityConjuredItemsDegradingTwiceAsFastForPassedSellInDates() {
        Item item1 = new Conjured(CONJURED, 0, 10);
        Item item2 = new Conjured(CONJURED, 2, 13);
        Item[] items = new Item[] { item1, item2 };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn, "Conjured item SellIn did not decrease correctly after sell-in date passed");
        assertEquals(6, app.items[0].quality, "Conjured item Quality did not decrease correctly after sell-in date passed");
        assertEquals(1, app.items[1].sellIn, "SellIn did not decrease as expected for Conjured item");
        assertEquals(11, app.items[1].quality, "Quality did not decrease as expected forConjured item");
    }

    @Test
    void testUpdateQualityThatQualityIsNeverNegativeForConjuredItems() {
        Item item1 = new Conjured(CONJURED, 2, 0);
        Item item2 = new Conjured(CONJURED, 0, 0);
        Item[] items = new Item[] { item1, item2 };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(1, app.items[0].sellIn, "SellIn did not decrease as expected");
        assertEquals(0, app.items[0].quality, "Quality should not be negative");
        assertEquals(-1, app.items[1].sellIn, "SellIn did not decrease as expected");
        assertEquals(0, app.items[1].quality, "Quality should not be negative");
    }
}
