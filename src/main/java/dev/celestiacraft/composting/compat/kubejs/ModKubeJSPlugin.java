package dev.celestiacraft.composting.compat.kubejs;

import dev.celestiacraft.composting.Composting;
import dev.celestiacraft.composting.compat.kubejs.recipe.FermentationBoxSchema;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class ModKubeJSPlugin extends KubeJSPlugin {
	@Override
	public void registerBindings(BindingsEvent event) {
		event.add("Composting", Composting.class);
	}

	@Override
	public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
		event.namespace(Composting.MODID)
				.register("fermentation_box", FermentationBoxSchema.SCHEMA);
	}
}