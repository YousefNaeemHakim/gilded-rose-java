package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    @Test
    void testUpdateQualityForRegularItem() {
        // Test that a regular item's quality and sellIn decrease by one.
        Item[] items = new Item[] { new Item("Regular Item", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void testUpdateQualityForAgedBrie() {
        // Test that Aged Brie's quality increases by one while sellIn decreases by one.
        Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void testUpdateQualityForBackstagePasses() {
        // Test Backstage Passes quality increases as sellIn approaches.
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(14, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void testUpdateQualityForSulfuras() {
        // Test that Sulfuras quality and sellIn remain unchanged.
        Item[] items = new Item[] {
                new Item("Sulfuras, Hand of Ragnaros", 5, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForConjuredItem() {
        // Test that Conjured items degrade in quality twice as fast as regular items.
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 6) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(2, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void testQualityNeverNegative() {
        // Test that the quality of any item never goes negative.
        Item[] items = new Item[] {
                new Item("Regular Item", 5, 0),
                new Item("Aged Brie", 5, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0),
                new Item("Conjured Mana Cake", 5, 0)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        for (Item item : app.items) {
            assertEquals(0, item.quality, "Quality should never be negative.");
        }
    }

    @Test
    void testQualityNeverExceeds50() {
        // Test that the quality of Aged Brie, Backstage Passes, and Conjured items never exceeds 50.
        Item[] items = new Item[] {
                new Item("Aged Brie", 5, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50),
                new Item("Conjured Mana Cake", 5, 50)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        for (Item item : app.items) {
            assertEquals(50, item.quality, "Quality should never exceed 50.");
        }
    }

    @Test
    void testSellInPassed() {
        // Test that the quality degrades twice as fast after the sellIn date has passed.
        Item[] items = new Item[] {
                new Item("Regular Item", 0, 10),
                new Item("Aged Brie", 0, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
                new Item("Conjured Mana Cake", 0, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        for (Item item : app.items) {
            assertEquals(8, item.quality, "Quality degrades twice as fast after sellIn passed.");
        }
    }

}
