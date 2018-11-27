package prospector.appetite.block;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import prospector.silk.util.SilkBlockBuilder;

public class BlockBerryBush extends LeavesBlock {

	public BlockBerryBush() {
		super(SilkBlockBuilder.create(Material.LEAVES).build());
	}
}
