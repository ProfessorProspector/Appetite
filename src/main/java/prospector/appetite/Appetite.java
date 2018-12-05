package prospector.appetite;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sortme.ItemGroup;
import net.minecraft.util.registry.Registry;

public class Appetite implements ModInitializer {
	public static final String MOD_ID = "appetite";

	static {
	}

	private static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCKS, MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Builder().itemGroup(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		register(name, item);
		return block;
	}

	private static Item register(String name, Item item) {
		Registry.register(Registry.ITEMS, MOD_ID + ":" + name, item);
		return item;
	}

	@Override
	public void onInitialize() {

	}
}
