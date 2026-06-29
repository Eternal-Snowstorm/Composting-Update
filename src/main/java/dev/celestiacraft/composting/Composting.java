package dev.celestiacraft.composting;

import dev.celestiacraft.composting.common.block.register.CompostingRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Composting.MODID)
public class Composting {
	public static final String MODID = "composting";
	public static final String NAME = "Composting";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	public static ResourceLocation loadResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	public Composting(FMLJavaModLoadingContext context) {
		IEventBus bus = context.getModEventBus();

		CompostingRegister.register(bus);

		context.registerConfig(ModConfig.Type.COMMON, CompostingConfig.SPEC, "nebula/composting/common.toml");
	}
}