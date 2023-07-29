package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Aged Brie")) {
                increaseQuality(item);
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                increaseQuality(item);
                if (item.sellIn < 11) {
                    increaseQuality(item);
                }
                if (item.sellIn < 6) {
                    increaseQuality(item);
                }
            } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                // As mentioned, Sulfuras doesn't change in quality or sellIn.
            } else {
                decreaseQuality(item);
            }

            decreaseSellIn(item);

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        decreaseQuality(item);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    increaseQuality(item);
                }
            }
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    private void decreaseSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
    }

}