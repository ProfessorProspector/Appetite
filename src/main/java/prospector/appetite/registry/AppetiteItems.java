package prospector.appetite.registry;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import prospector.appetite.Appetite;

public class AppetiteItems implements ModInitializer {

	public static Item register(String name, Item item) {
		Registry.register(Registry.ITEMS, Appetite.MOD_ID + ":" + name, item);
		return item;
	}

	@Override
	public void onInitialize() { }
}
