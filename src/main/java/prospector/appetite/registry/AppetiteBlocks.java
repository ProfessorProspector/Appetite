package prospector.appetite.registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import prospector.appetite.Appetite;
import prospector.appetite.block.CounterBlock;

public class AppetiteBlocks implements ModInitializer {
	public static final Block COUNTER = register("counter", new CounterBlock(FabricBlockSettings.create(Material.WOOD).setHardness(2.5F).setSoundGroup(BlockSoundGroup.WOOD).build()), ItemGroup.DECORATIONS);

	public static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCKS, Appetite.MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		AppetiteItems.register(name, item);
		return block;
	}

	@Override
	public void onInitialize() { }
}
