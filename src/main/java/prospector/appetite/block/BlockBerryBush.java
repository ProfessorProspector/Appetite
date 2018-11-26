package prospector.appetite.block;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.Material;
import prospector.silk.util.SilkBlockBuilder;

public class BlockBerryBush extends BlockLeaves {

	public BlockBerryBush() {
		super(SilkBlockBuilder.create(Material.LEAVES).build());
	}
}
