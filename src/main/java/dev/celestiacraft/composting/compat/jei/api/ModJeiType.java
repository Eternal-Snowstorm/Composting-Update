package dev.celestiacraft.composting.compat.jei.api;

import dev.celestiacraft.composting.Composting;
import dev.celestiacraft.composting.common.block.fermentation_box.FBBRecipe;
import mezz.jei.api.recipe.RecipeType;

public class ModJeiType {
	public static final RecipeType<FBBRecipe> FERMENTATION_BOX;

	static {
		FERMENTATION_BOX = addJeiRecipeType("fermentation_box", FBBRecipe.class);
	}

	private static <T> RecipeType<T> addJeiRecipeType(String path, Class<? extends T> recipeClass) {
		return RecipeType.create(Composting.MODID, path, recipeClass);
	}
}