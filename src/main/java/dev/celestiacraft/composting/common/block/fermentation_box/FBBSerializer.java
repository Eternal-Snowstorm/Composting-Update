package dev.celestiacraft.composting.common.block.fermentation_box;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FBBSerializer implements RecipeSerializer<FBBRecipe> {
	@Override
	public @NotNull FBBRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
		Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
		double chance = GsonHelper.getAsDouble(json, "chance");

		return new FBBRecipe(id, ingredient,  chance);
	}

	@Override
	public @Nullable FBBRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
		Ingredient ingredient = Ingredient.fromNetwork(buf);
		double chance = buf.readDouble();

		return new FBBRecipe(id, ingredient,  chance);
	}

	@Override
	public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull FBBRecipe recipe) {
		recipe.getIngredient().toNetwork(buf);
		buf.writeDouble(recipe.getChance());
	}
}