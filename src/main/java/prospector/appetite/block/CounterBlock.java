package prospector.appetite.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Facing;
import net.minecraft.world.World;
import prospector.appetite.Appetite;
import prospector.appetite.container.CounterContainer;
import teamreborn.assembly.container.AssemblyContainerHelper;
import teamreborn.assembly.container.FabricContainerProvider;

public class CounterBlock extends HorizontalFacingBlock {
	public CounterBlock(Block.Settings var1) {
		super(var1);
	}

	@Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, Facing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			BlockPos centerPos = pos.add(0.5D, 0.0D, 0.5D);
			AssemblyContainerHelper.openGui(new CounterContainerProvider(centerPos), centerPos, (ServerPlayerEntity) player);
			return true;
		}
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(field_11177, context.getPlayerHorizontalFacing());
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.with(field_11177);
	}

	public static class CounterContainerProvider implements FabricContainerProvider {
		private final BlockPos pos;

		public CounterContainerProvider(BlockPos pos) {
			this.pos = pos;
		}

		@Override
		public Container createContainer(PlayerEntity playerEntity) {
			return new CounterContainer(playerEntity.inventory, pos);
		}

		@Override
		public Identifier getContainerIdentifier() {
			return new Identifier(Appetite.MOD_ID, "counter");
		}
	}
}
