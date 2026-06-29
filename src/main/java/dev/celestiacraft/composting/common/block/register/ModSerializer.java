package dev.celestiacraft.composting.common.block.register;

import dev.celestiacraft.composting.common.block.fermentation_box.FBBRecipe;
import dev.celestiacraft.composting.common.block.fermentation_box.FBBSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModSerializer {
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS;

	public static final Supplier<RecipeSerializer<FBBRecipe>> FERMENTATION_BOX;

	static {
		SERIALIZERS = CompostingRegister.create(ForgeRegistries.RECIPE_SERIALIZERS);

		FERMENTATION_BOX = SERIALIZERS.register("fermentation_box", FBBSerializer::new);
	}
}