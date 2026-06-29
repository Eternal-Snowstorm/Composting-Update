package dev.celestiacraft.composting.common.block.fermentation_box;

import dev.celestiacraft.composting.common.block.register.ModRecipeType;
import dev.celestiacraft.composting.common.block.register.ModSerializer;
import lombok.Getter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

@Getter
public class FBBRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private final Ingredient ingredient;
	private final double chance;

	public FBBRecipe(ResourceLocation id, Ingredient ingredient, double chance) {
		this.id = id;
		this.ingredient = ingredient;
		this.chance = chance;
	}

	public boolean roll(RandomSource random) {
		return random.nextFloat() < chance;
	}

	@Override
	public boolean matches(@NotNull Container container, @NotNull Level level) {
		return false;
	}

	@Override
	public @NotNull ItemStack assemble(@NotNull Container container, @NotNull RegistryAccess access) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public @NotNull ItemStack getResultItem(@NotNull RegistryAccess access) {
		return ItemStack.EMPTY;
	}

	@Override
	public @NotNull ResourceLocation getId() {
		return id;
	}

	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return ModSerializer.FERMENTATION_BOX.get();
	}

	@Override
	public @NotNull RecipeType<?> getType() {
		return ModRecipeType.FERMENTATION_BOX.get();
	}
}