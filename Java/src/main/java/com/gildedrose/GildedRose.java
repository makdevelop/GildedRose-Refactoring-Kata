package com.gildedrose;

import java.util.Arrays;

class GildedRose {
	
	private Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {

		Arrays.asList(items).forEach(item -> {
			EnumItem enumItem = EnumItem.fromString(item.name) ;
			int value = enumItem.getValue();
			int maxQuality = enumItem.getMaxQuality();
			int minQuality = enumItem.getMinQuality();
			if(EnumItem.BACKSTAGE.equals(enumItem)) {
				if (item.sellIn <= 0) {
					maxQuality = 0;
				} else if (item.sellIn < 6) {
					value = 3;
				} else if (item.sellIn < 11) {
					value =2 ;
				}
			}else if (EnumItem.AUTRE.equals(enumItem) && item.sellIn <= 0 ) {
					value = -2;
			}
			
			if( !enumItem.isLegendary()) {
				--item.sellIn;
			}
			update(item ,value,minQuality, maxQuality );

		});
	}

	
	
	private void update(Item item , int value, int minQuality, int maxQuality) {
		if (item.quality + value < minQuality) {
			item.quality = minQuality;
		} else if (item.quality + value > maxQuality) {
			item.quality = maxQuality ;
		} else if (item.quality > minQuality && item.quality < maxQuality) {
			item.quality = item.quality + value;
		}
	}
	
	public Item[] getItems() {
		return this.items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
}