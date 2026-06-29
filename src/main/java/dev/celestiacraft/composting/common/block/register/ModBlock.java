package dev.celestiacraft.composting.common.block.register;

import dev.celestiacraft.composting.common.block.fermentation_box.FBBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlock {
	public static final DeferredRegister<Block> BLOCKS;
	public static final DeferredRegister<Item> ITEMS;

	public static final Supplier<FBBlock> FERMENTATION_BOX;

	static {
		BLOCKS = CompostingRegister.create(ForgeRegistries.BLOCKS);
		ITEMS = CompostingRegister.create(ForgeRegistries.ITEMS);

		FERMENTATION_BOX = register("fermentation_box", FBBlock::new);
	}

	private static <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
		Supplier<T> registered = BLOCKS.register(name, block);
		ITEMS.register(name, () -> {
			return new BlockItem(registered.get(), new Item.Properties());
		});
		return registered;
	}
}