package prospector.appetite.client.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.audio.PositionedSoundEvent;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.TextureCache;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BannerItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.Sounds;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import prospector.appetite.container.CounterContainer;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class CounterGui extends ContainerGui implements InventoryListener {
	private static final Identifier TEXTURE = new Identifier("textures/gui/container/loom.png");
	private static final int field_2963;
	private static final DyeColor field_2964;
	private static final DyeColor field_2956;
	private static final ArrayList<DyeColor> field_2959;
	private final PlayerInventory field_2960;
	private final CounterContainer field_2971;
	private Identifier field_2957;
	private ItemStack field_2955;
	private ItemStack field_2954;
	private ItemStack field_2967;
	private final Identifier[] field_2972;
	private boolean field_2965;
	private boolean field_2962;
	private boolean field_2961;
	private float field_2968;
	private boolean field_2958;
	private int field_2970;
	private int field_2969;

	public CounterGui(PlayerInventory playerInventory) {
		super(new CounterContainer(playerInventory, null));
		this.field_2955 = ItemStack.EMPTY;
		this.field_2954 = ItemStack.EMPTY;
		this.field_2967 = ItemStack.EMPTY;
		this.field_2972 = new Identifier[BannerPattern.COUNT];
		this.field_2970 = 1;
		this.field_2969 = 1;
		this.field_2960 = playerInventory;
		this.field_2971 = (CounterContainer) this.container;
		((BasicInventory) this.field_2971.inventory).addListener(this);
	}

	@Override
	public void update() {
		super.update();
		if (this.field_2969 < BannerPattern.COUNT) {
			BannerPattern var1 = BannerPattern.values()[this.field_2969];
			String var2 = "b" + field_2964.getId();
			String var3 = var1.getId() + field_2956.getId();
			this.field_2972[this.field_2969] = TextureCache.BANNER.get(var2 + var3, Lists.newArrayList(BannerPattern.BASE, var1), field_2959);
			++this.field_2969;
		}

	}

	@Override
	public void draw(int var1, int var2, float var3) {
		super.draw(var1, var2, var3);
		this.drawMousoverTooltip(var1, var2);
	}

	@Override
	protected void drawForeground(int var1, int var2) {
		this.fontRenderer.draw(I18n.translate("container.loom"), 8.0F, 4.0F, 4210752);
		this.fontRenderer.draw(this.field_2960.getDisplayName().getFormattedText(), 8.0F, (float) (this.containerHeight - 96 + 2), 4210752);
	}

	@Override
	protected void drawBackground(float var1, int var2, int var3) {
		this.drawBackground();
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(TEXTURE);
		int var4 = this.left;
		int var5 = this.top;
		this.drawTexturedRect(var4, var5, 0, 0, this.containerWidth, this.containerHeight);
		Slot var6 = this.field_2971.getSlot(0);
		Slot var7 = this.field_2971.getSlot(1);
		Slot var8 = this.field_2971.getSlot(2);
		Slot var9 = this.field_2971.getSlot(3);
		if (!var6.hasStack()) {
			this.drawTexturedRect(var4 + var6.xPosition, var5 + var6.yPosition, this.containerWidth, 0, 16, 16);
		}

		if (!var7.hasStack()) {
			this.drawTexturedRect(var4 + var7.xPosition, var5 + var7.yPosition, this.containerWidth + 16, 0, 16, 16);
		}

		if (!var8.hasStack()) {
			this.drawTexturedRect(var4 + var8.xPosition, var5 + var8.yPosition, this.containerWidth + 32, 0, 16, 16);
		}

		int var10 = (int) (41.0F * this.field_2968);
		this.drawTexturedRect(var4 + 119, var5 + 13 + var10, 232 + (this.field_2965 ? 0 : 12), 0, 12, 15);
		if (this.field_2957 != null && !this.field_2961) {
			this.client.getTextureManager().bindTexture(this.field_2957);
			drawTexturedRect(var4 + 141, var5 + 8, 1.0F, 1.0F, 20, 40, 20, 40, 64.0F, 64.0F);
		} else if (this.field_2961) {
			this.drawTexturedRect(var4 + var9.xPosition - 2, var5 + var9.yPosition - 2, this.containerWidth, 17, 17, 16);
		}

		int var11;
		int var12;
		int var13;
		if (this.field_2965) {
			var11 = var4 + 60;
			var12 = var5 + 13;
			var13 = this.field_2970 + 16;

			for (int var14 = this.field_2970; var14 < var13 && var14 < this.field_2972.length - 4; ++var14) {
				int var15 = var14 - this.field_2970;
				int var16 = var11 + var15 % 4 * 14;
				int var17 = var12 + var15 / 4 * 14;
				this.client.getTextureManager().bindTexture(TEXTURE);
				int var18 = this.containerHeight;
				if (var14 == this.field_2971.method_7647()) {
					var18 += 14;
				} else if (var2 >= var16 && var3 >= var17 && var2 < var16 + 14 && var3 < var17 + 14) {
					var18 += 28;
				}

				this.drawTexturedRect(var16, var17, 0, var18, 14, 14);
				if (this.field_2972[var14] != null) {
					this.client.getTextureManager().bindTexture(this.field_2972[var14]);
					drawTexturedRect(var16 + 4, var17 + 2, 1.0F, 1.0F, 20, 40, 5, 10, 64.0F, 64.0F);
				}
			}
		} else if (this.field_2962) {
			var11 = var4 + 60;
			var12 = var5 + 13;
			this.client.getTextureManager().bindTexture(TEXTURE);
			this.drawTexturedRect(var11, var12, 0, this.containerHeight, 14, 14);
			var13 = this.field_2971.method_7647();
			if (this.field_2972[var13] != null) {
				this.client.getTextureManager().bindTexture(this.field_2972[var13]);
				drawTexturedRect(var11 + 4, var12 + 2, 1.0F, 1.0F, 20, 40, 5, 10, 64.0F, 64.0F);
			}
		}

	}

	@Override
	public boolean mouseClicked(double var1, double var3, int var5) {
		this.field_2958 = false;
		if (this.field_2965) {
			int var6 = this.left + 60;
			int var7 = this.top + 13;
			int var8 = this.field_2970 + 16;

			for (int var9 = this.field_2970; var9 < var8; ++var9) {
				int var10 = var9 - this.field_2970;
				double var11 = var1 - (double) (var6 + var10 % 4 * 14);
				double var13 = var3 - (double) (var7 + var10 / 4 * 14);
				if (var11 >= 0.0D && var13 >= 0.0D && var11 < 14.0D && var13 < 14.0D && this.field_2971.onButtonClick(this.client.player, var9)) {
					MinecraftClient.getInstance().getSoundLoader().play(PositionedSoundEvent.method_4758(Sounds.UI_LOOM_SELECT_PATTERN, 1.0F));
					this.client.interactionManager.clickButton(this.field_2971.syncId, var9);
					return true;
				}
			}

			var6 = this.left + 119;
			var7 = this.top + 9;
			if (var1 >= (double) var6 && var1 < (double) (var6 + 12) && var3 >= (double) var7 && var3 < (double) (var7 + 56)) {
				this.field_2958 = true;
			}
		}

		return super.mouseClicked(var1, var3, var5);
	}

	@Override
	public boolean mouseDragged(double var1, double var3, int var5, double var6, double var8) {
		if (this.field_2958 && this.field_2965) {
			int var10 = this.top + 13;
			int var11 = var10 + 56;
			this.field_2968 = ((float) var3 - (float) var10 - 7.5F) / ((float) (var11 - var10) - 15.0F);
			this.field_2968 = MathHelper.clamp(this.field_2968, 0.0F, 1.0F);
			int var12 = field_2963 - 4;
			int var13 = (int) ((double) (this.field_2968 * (float) var12) + 0.5D);
			if (var13 < 0) {
				var13 = 0;
			}

			this.field_2970 = 1 + var13 * 4;
			return true;
		} else {
			return super.mouseDragged(var1, var3, var5, var6, var8);
		}
	}

	@Override
	public boolean mouseScrolled(double var1) {
		if (this.field_2965) {
			int var3 = field_2963 - 4;
			this.field_2968 = (float) ((double) this.field_2968 - var1 / (double) var3);
			this.field_2968 = MathHelper.clamp(this.field_2968, 0.0F, 1.0F);
			this.field_2970 = 1 + (int) ((double) (this.field_2968 * (float) var3) + 0.5D) * 4;
		}

		return true;
	}

	@Override
	protected boolean isClickInContainerBounds(double var1, double var3, int var5, int var6, int var7) {
		return var1 < (double) var5 || var3 < (double) var6 || var1 >= (double) (var5 + this.containerWidth) || var3 >= (double) (var6 + this.containerHeight);
	}

	@Override
	public void onInvChange(Inventory var1) {
		ItemStack var2 = var1.getInvStack(3);
		if (var2.isEmpty()) {
			this.field_2957 = null;
		} else {
			BannerBlockEntity var3 = new BannerBlockEntity();
			var3.deserialize(var2, ((BannerItem) var2.getItem()).getColor());
			this.field_2957 = TextureCache.BANNER.get(var3.method_10915(), var3.getPatternList(), var3.getPatternColorList());
		}

		ItemStack var7 = var1.getInvStack(0);
		ItemStack var4 = var1.getInvStack(1);
		ItemStack var5 = var1.getInvStack(2);
		CompoundTag var6 = var7.getOrCreateSubCompoundTag("BlockEntityTag");
		this.field_2961 = var6.containsKey("Patterns", 9) && !var7.isEmpty() && var6.getList("Patterns", 10).size() >= 6;
		if (this.field_2961) {
			this.field_2957 = null;
		}

		if (!ItemStack.areEqual(var7, this.field_2955) || !ItemStack.areEqual(var4, this.field_2954) || !ItemStack.areEqual(var5, this.field_2967)) {
			this.field_2965 = !var7.isEmpty() && !var4.isEmpty() && var5.isEmpty() && !this.field_2961;
			this.field_2962 = !var5.isEmpty() && !this.field_2961;
		}

		this.field_2955 = var7.copy();
		this.field_2954 = var4.copy();
		this.field_2967 = var5.copy();
	}

	static {
		field_2963 = (BannerPattern.COUNT - 4 - 1 + 4 - 1) / 4;
		field_2964 = DyeColor.GRAY;
		field_2956 = DyeColor.WHITE;
		field_2959 = Lists.newArrayList(field_2964, field_2956);
	}
}
