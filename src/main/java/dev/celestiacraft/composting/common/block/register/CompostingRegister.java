package dev.celestiacraft.composting.common.block.register;

import dev.celestiacraft.composting.Composting;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

public class CompostingRegister {
	public static <B> DeferredRegister<B> create(IForgeRegistry<B> registry) {
		return DeferredRegister.create(registry, Composting.MODID);
	}

	public static void register(IEventBus bus) {
		Composting.LOGGER.info("{} Registered!", Composting.NAME);

		ModBlock.BLOCKS.register(bus);
		ModBlock.ITEMS.register(bus);
		ModItem.ITEMS.register(bus);
		ModBlockEntity.BLOCK_ENTITY.register(bus);
		ModRecipeType.RECIPE_TYPES.register(bus);
		ModSerializer.SERIALIZERS.register(bus);
	}
}