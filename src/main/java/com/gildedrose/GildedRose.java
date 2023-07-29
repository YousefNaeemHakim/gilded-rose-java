package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater itemUpdater = getItemUpdater(item);
            itemUpdater.updateQuality(item);

            if (item.quality < 0) {
                item.quality = 0;
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 50) {
                item.quality = 50;
            }
        }
    }

    private ItemUpdater getItemUpdater(Item item) {
        // Determine the appropriate updater for each item based on its name.
        if (item.name.equals("Aged Brie")) {
            return new AgedBrieUpdater();
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new BackstagePassUpdater();
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return new SulfurasUpdater();
        } else if (item.name.equals("Conjured Mana Cake")) { // Corrected the item name check
            return new ConjuredItemUpdater();
        } else {
            // Default updater
            return new RegularItemUpdater();
        }
    }

    interface ItemUpdater {
        void updateQuality(Item item);
    }

    class AgedBrieUpdater implements ItemUpdater {
        @Override
        public void updateQuality(Item item) {
            increaseQuality(item);
            decreaseSellIn(item);
            if (item.sellIn < 0) {
                increaseQuality(item);
            }
        }
    }

    class BackstagePassUpdater implements ItemUpdater {
        @Override
        public void updateQuality(Item item) {
            increaseQuality(item);
            if (item.sellIn < 11) {
                increaseQuality(item);
            }
            if (item.sellIn < 6) {
                increaseQuality(item);
            }
            decreaseSellIn(item);
            if (item.sellIn < 0) {
                item.quality = 0;
            }
        }
    }

    class SulfurasUpdater implements ItemUpdater {
        @Override
        public void updateQuality(Item item) {
            // Sulfuras never changes in quality or sellIn
        }
    }

    class ConjuredItemUpdater implements ItemUpdater {
        @Override
        public void updateQuality(Item item) {
            decreaseQuality(item);
            decreaseQuality(item);
            decreaseSellIn(item);
            if (item.sellIn < 0) {
                decreaseQuality(item);
                decreaseQuality(item);
            }
        }
    }

    class RegularItemUpdater implements ItemUpdater {
        @Override
        public void updateQuality(Item item) {
            decreaseQuality(item);
            decreaseSellIn(item);
            if (item.sellIn < 0) {
                decreaseQuality(item);
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