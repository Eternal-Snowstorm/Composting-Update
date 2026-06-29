package dev.celestiacraft.composting.compat.kubejs.recipe;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface FermentationBoxSchema {
	RecipeKey<InputItem[]> INPUT = ItemComponents.INPUT_ARRAY.key("ingredient");
	RecipeKey<Double> CHANCE = NumberComponent.DOUBLE.key("chance");

	RecipeSchema SCHEMA = new RecipeSchema(INPUT, CHANCE);
}