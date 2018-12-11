package io.github.prospector.appetite.registry;

import io.github.prospector.appetite.Appetite;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import io.github.prospector.appetite.blockentity.CounterBlockEntity;

public class AppetiteBlockEntities implements ModInitializer {
	public static final BlockEntityType<CounterBlockEntity> COUNTER = register("counter", BlockEntityType.Builder.create(CounterBlockEntity::new));

	public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
		BlockEntityType<T> blockEntityType = builder.method_11034(null);
		Registry.register(Registry.BLOCK_ENTITIES, Appetite.MOD_ID + ":" + name, blockEntityType);
		return blockEntityType;
	}

	@Override
	public void onInitialize() {
	}
}
