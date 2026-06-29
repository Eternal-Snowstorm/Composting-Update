package dev.celestiacraft.composting;

import net.minecraftforge.common.ForgeConfigSpec;

public class CompostingConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec.IntValue PROGRESS_TIME;
	public static final ForgeConfigSpec.ConfigValue<String> RESULT_ITEM;
	public static final ForgeConfigSpec.IntValue RESULT_COUNT;
	public static final ForgeConfigSpec.ConfigValue<String> RESULT_NBT;

	public static final ForgeConfigSpec SPEC;

	static {
		BUILDER.comment("All settings below will only take effect after restarting the server or client.")
				.push("general");

		PROGRESS_TIME = BUILDER
				.comment("Time required for fermentation")
				.defineInRange("progressTime", 200, 1, Integer.MAX_VALUE);

		RESULT_ITEM = BUILDER
				.comment("Fermentation result item")
				.define("resultItem", "minecraft:bone_meal");

		RESULT_COUNT = BUILDER
				.comment("Fermentation result count")
				.defineInRange("resultCount", 1, 1, 64);

		RESULT_NBT = BUILDER
				.comment("SNBT of the result item")
				.define("resultNbt", "{}");

		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}