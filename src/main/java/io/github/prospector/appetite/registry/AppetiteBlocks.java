package io.github.prospector.appetite.registry;

import io.github.prospector.appetite.Appetite;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public class AppetiteBlocks implements ModInitializer {

	public static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCK, Appetite.MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		AppetiteItems.register(name, item);
		return block;
	}

	@Override
	public void onInitialize() { }
}
