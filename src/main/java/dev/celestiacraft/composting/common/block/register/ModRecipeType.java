package dev.celestiacraft.composting.common.block.register;

import dev.celestiacraft.composting.Composting;
import dev.celestiacraft.composting.common.block.fermentation_box.FBBRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModRecipeType {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

	public static final Supplier<RecipeType<FBBRecipe>> FERMENTATION_BOX;

	static {
		RECIPE_TYPES = CompostingRegister.create(ForgeRegistries.RECIPE_TYPES);

		FERMENTATION_BOX = register("fermentation_box");
	}

	private static <T extends Recipe<?>> Supplier<RecipeType<T>> register(String name) {
		return RECIPE_TYPES.register(name, () -> new RecipeType<>() {
			@Override
			public String toString() {
				return Composting.loadResource(name).toString();
			}
		});
	}
}