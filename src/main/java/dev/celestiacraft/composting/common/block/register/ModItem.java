package dev.celestiacraft.composting.common.block.register;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItem {
	public static final DeferredRegister<Item> ITEMS;

	static {
		ITEMS = CompostingRegister.create(ForgeRegistries.ITEMS);
	}
}