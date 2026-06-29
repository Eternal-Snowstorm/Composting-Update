package dev.celestiacraft.composting.compat.jei.category;

import dev.celestiacraft.composting.common.block.fermentation_box.FBBRecipe;
import dev.celestiacraft.composting.common.block.register.ModBlock;
import dev.celestiacraft.composting.compat.jei.api.ModJeiType;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FermentationBoxCategory implements IRecipeCategory<FBBRecipe> {
	private final IDrawable icon;

	public FermentationBoxCategory(IGuiHelper helper) {
		icon = helper.createDrawableItemLike(ModBlock.FERMENTATION_BOX.get());
	}

	@Override
	public @NotNull RecipeType<FBBRecipe> getRecipeType() {
		return ModJeiType.FERMENTATION_BOX;
	}

	@Override
	public @NotNull Component getTitle() {
		return Component.translatable("jei.category.composting.fermentation_box");
	}

	@Override
	public @Nullable IDrawable getIcon() {
		return icon;
	}

	@Override
	public int getWidth() {
		return 120;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull FBBRecipe recipe, @NotNull IFocusGroup group) {
		builder.addInputSlot(1, 1)
				.setStandardSlotBackground()
				.addIngredients(recipe.getIngredient());
	}

	@Override
	public void createRecipeExtras(@NotNull IRecipeExtrasBuilder builder, @NotNull FBBRecipe recipe, @NotNull IFocusGroup group) {
		double chance = recipe.getChance();
		int chancePercent = (int) Math.floor(chance * 100);
		Component text = Component.translatable("gui.jei.category.compostable.chance", chancePercent);
		builder.addText(text, getWidth() - 24, getHeight())
				.setPosition(24, 0)
				.setTextAlignment(HorizontalAlignment.CENTER)
				.setTextAlignment(VerticalAlignment.CENTER)
				.setColor(0xFF808080);
	}
}