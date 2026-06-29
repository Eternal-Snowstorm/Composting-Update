package dev.celestiacraft.composting.event;

import dev.celestiacraft.composting.common.block.register.ModBlock;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class addCreativeTabs {
	@SubscribeEvent
	public static void buildContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
			event.accept(ModBlock.FERMENTATION_BOX.get());
		}
	}
}