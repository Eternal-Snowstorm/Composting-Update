package dev.celestiacraft.composting.compat.jei;

import dev.celestiacraft.composting.Composting;
import dev.celestiacraft.composting.common.block.fermentation_box.FBBRecipe;
import dev.celestiacraft.composting.common.block.register.ModBlock;
import dev.celestiacraft.composting.common.block.register.ModRecipeType;
import dev.celestiacraft.composting.compat.jei.api.ModJeiType;
import dev.celestiacraft.composting.compat.jei.category.FermentationBoxCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return Composting.loadResource("jei_plugin");
	}

	@Override
	public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

		registration.addRecipeCategories(
				new FermentationBoxCategory(helper)
		);
	}

	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registration) {
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

		List<FBBRecipe> fermentationBoxRecipe = manager.getAllRecipesFor(ModRecipeType.FERMENTATION_BOX.get());

		registration.addRecipes(ModJeiType.FERMENTATION_BOX, fermentationBoxRecipe);
	}

	@Override
	public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
		IJeiHelpers helpers = registration.getJeiHelpers();

		registration.addRecipeCatalyst(
				ModBlock.FERMENTATION_BOX.get(),
				ModJeiType.FERMENTATION_BOX
		);
	}
}