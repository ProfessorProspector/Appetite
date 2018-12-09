package prospector.appetite.container;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.class_1662;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.CraftingContainer;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BannerItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.Sounds;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CounterContainer extends CraftingContainer {
	private final World world;
	private final BlockPos pos;
	private int field_7847;
	public Inventory inventory = new BasicInventory(new StringTextComponent("Counter"), 4) {
		@Override
		public void markDirty() {
			super.markDirty();
			CounterContainer.this.onContentChanged(this);
		}

		@Override
		public void setInvProperty(int var1, int var2) {
			CounterContainer.this.setProperty(var1, var2);
		}
	};

	public CounterContainer(PlayerInventory playerInventory, final BlockPos pos) {
		this.world = playerInventory.player.world;
		this.pos = pos;
		this.addSlot(new Slot(this.inventory, 0, 13, 26) {
			@Override
			public boolean canInsert(ItemStack stack) {
				return stack.getItem() instanceof BannerItem;
			}
		});
		this.addSlot(new Slot(this.inventory, 1, 33, 26) {
			@Override
			public boolean canInsert(ItemStack stack) {
				return stack.getItem() instanceof DyeItem;
			}
		});
		this.addSlot(new Slot(this.inventory, 2, 23, 45) {
			@Override
			public boolean canInsert(ItemStack stack) {
				return stack.getItem() instanceof BannerPatternItem;
			}
		});
		this.addSlot(new Slot(this.inventory, 3, 143, 58) {
			@Override
			public boolean canInsert(ItemStack stack) {
				return false;
			}

			@Override
			public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
				this.inventory.takeInvStack(0, 1);
				this.inventory.takeInvStack(1, 1);
				this.inventory.setInvProperty(1, 0);
				if (pos != null) {
					CounterContainer.this.world.playSound(null, pos, Sounds.UI_LOOM_TAKE_RESULT, SoundCategory.BLOCK, 1.0F, 1.0F);
				}

				return super.onTakeItem(player, stack);
			}
		});

		int var3;
		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlot(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlot(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
		}

	}

	@Override
	public int getCraftingResultSlotIndex() {
		return 3;
	}

	@Override
	public int getCraftingWidth() {
		return 1;
	}

	@Override
	public int getCrafitngHeight() {
		return 1;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int method_7658() {
		return 3;
	}

	@Override
	public void sendContentUpdates() {
		for (ContainerListener listener : this.listeners) {
			listener.onContainerPropertyUpdate(this, 1, this.field_7847);
		}
		super.sendContentUpdates();
	}

	@Override
	public void method_7654(class_1662 var1) {
	}

	@Override
	public void setProperty(int var1, int var2) {
		if (var1 == 1) {
			this.field_7847 = var2;
		}

	}

	@Environment(EnvType.CLIENT)
	public int method_7647() {
		return this.field_7847;
	}

	@Override
	public boolean canUse(PlayerEntity var1) {
		if (this.world.getBlockState(this.pos).getBlock() != Blocks.LOOM) {
			return false;
		} else {
			return var1.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clearCraftingSlots() {
		this.inventory.clearInv();
	}

	@Override
	public boolean matches(Recipe var1) {
		return false;
	}

	@Override
	public boolean onButtonClick(PlayerEntity var1, int var2) {
		this.setProperty(1, var2);
		this.method_7648();
		return true;
	}

	@Override
	public void onContentChanged(Inventory var1) {
		ItemStack var2 = this.inventory.getInvStack(0);
		ItemStack var3 = this.inventory.getInvStack(1);
		ItemStack var4 = this.inventory.getInvStack(2);
		ItemStack var5 = this.inventory.getInvStack(3);
		if (!var5.isEmpty() && (var2.isEmpty() || var3.isEmpty() || this.field_7847 <= 0 || this.field_7847 >= BannerPattern.COUNT - 4 && var4.isEmpty())) {
			this.inventory.setInvStack(3, ItemStack.EMPTY);
			this.field_7847 = 0;
			this.sendContentUpdates();
		} else if (!var4.isEmpty() && var4.getItem() instanceof BannerPatternItem) {
			CompoundTag var6 = var2.getOrCreateSubCompoundTag("BlockEntityTag");
			boolean var7 = var6.containsKey("Patterns", 9) && !var2.isEmpty() && var6.getList("Patterns", 10).size() >= 6;
			if (var7) {
				this.field_7847 = 0;
			} else {
				this.field_7847 = ((BannerPatternItem) var4.getItem()).method_7704().ordinal();
				this.method_7648();
			}

			this.sendContentUpdates();
		}

	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int slot) {
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = this.slotList.get(slot);
		if (var4 != null && var4.hasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (slot == 3) {
				if (!this.insertItem(var5, 4, 40, true)) {
					return ItemStack.EMPTY;
				}

				var4.onStackChanged(var5, var3);
			} else if (slot != 1 && slot != 0 && slot != 2) {
				if (var5.getItem() instanceof BannerItem) {
					if (!this.insertItem(var5, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (var5.getItem() instanceof DyeItem) {
					if (!this.insertItem(var5, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (var5.getItem() instanceof BannerPatternItem) {
					if (!this.insertItem(var5, 2, 3, false)) {
						return ItemStack.EMPTY;
					}
				} else if (slot >= 4 && slot < 31) {
					if (!this.insertItem(var5, 31, 40, false)) {
						return ItemStack.EMPTY;
					}
				} else if (slot >= 31 && slot < 40 && !this.insertItem(var5, 4, 31, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(var5, 4, 40, false)) {
				return ItemStack.EMPTY;
			}

			if (var5.isEmpty()) {
				var4.setStack(ItemStack.EMPTY);
			} else {
				var4.markDirty();
			}

			if (var5.getAmount() == var3.getAmount()) {
				return ItemStack.EMPTY;
			}

			var4.onTakeItem(player, var5);
		}

		return var3;
	}

	@Override
	public void close(PlayerEntity var1) {
		super.close(var1);
		if (!this.world.isRemote) {
			this.inventory.removeInvStack(3);
			this.method_7607(var1, var1.world, this.inventory);
		}
	}

	private void method_7648() {
		if (!this.world.isRemote && this.field_7847 > 0) {
			ItemStack var1 = this.inventory.getInvStack(0);
			ItemStack var2 = this.inventory.getInvStack(1);
			ItemStack var3 = ItemStack.EMPTY;
			if (!var1.isEmpty() && !var2.isEmpty()) {
				var3 = var1.copy();
				var3.setAmount(1);
				BannerPattern var4 = BannerPattern.values()[this.field_7847];
				DyeColor var5 = ((DyeItem) var2.getItem()).getColor();
				CompoundTag var6 = var3.getOrCreateSubCompoundTag("BlockEntityTag");
				ListTag var7;
				if (var6.containsKey("Patterns", 9)) {
					var7 = var6.getList("Patterns", 10);
				} else {
					var7 = new ListTag();
					var6.put("Patterns", var7);
				}

				CompoundTag var8 = new CompoundTag();
				var8.putString("Pattern", var4.getId());
				var8.putInt("Color", var5.getId());
				var7.add(var8);
			}

			if (!ItemStack.areEqual(var3, this.inventory.getInvStack(3))) {
				this.inventory.setInvStack(3, var3);
			}
		}

	}
}
