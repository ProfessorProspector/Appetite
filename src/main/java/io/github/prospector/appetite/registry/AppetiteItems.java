package io.github.prospector.appetite.registry;

import io.github.prospector.appetite.Appetite;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class AppetiteItems implements ModInitializer {

	public static final Item APPLE_PIE = register("apple_pie", new FoodItem(7, 0.3F, false, new Item.Settings().itemGroup(ItemGroup.FOOD)));

	public static Item register(String name, Item item) {
		Registry.register(Registry.ITEM, Appetite.MOD_ID + ":" + name, item);
		return item;
	}

	@Override
	public void onInitialize() { }
}
