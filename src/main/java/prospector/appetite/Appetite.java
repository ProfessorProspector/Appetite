package prospector.appetite;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.gui.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.block.ItemBlock;
import net.minecraft.util.registry.Registry;
import prospector.appetite.block.BlockBerryBush;

public class Appetite implements ModInitializer {
	public static final String MOD_ID = "appetite";

	public static final Block BLUEBERRY_BUSH;
	public static final Block BLACKBERRY_BUSH;
	public static final Block RASPBERRY_BUSH;

	static {
		BLUEBERRY_BUSH = register("blueberry_bush", new BlockBerryBush(), ItemGroup.DECORATIONS);
		BLACKBERRY_BUSH = register("blackberry_bush", new BlockBerryBush(), ItemGroup.DECORATIONS);
		RASPBERRY_BUSH = register("raspberry_bush", new BlockBerryBush(), ItemGroup.DECORATIONS);
	}

	private static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCKS, MOD_ID + ":" + name, block);
		ItemBlock item = new ItemBlock(block, new Item.Builder().creativeTab(tab));
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
