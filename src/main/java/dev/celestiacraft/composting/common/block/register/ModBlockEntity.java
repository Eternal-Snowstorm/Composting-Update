package dev.celestiacraft.composting.common.block.register;

import dev.celestiacraft.composting.common.block.fermentation_box.FBBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockEntity {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY;

	public static final Supplier<BlockEntityType<FBBlockEntity>> FERMENTATION_BOX;

	static {
		BLOCK_ENTITY = CompostingRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES);

		FERMENTATION_BOX = BLOCK_ENTITY.register("fermentation_box", () -> {
			return BlockEntityType.Builder.of(
					FBBlockEntity::new,
					ModBlock.FERMENTATION_BOX.get()
			).build(null);
		});
	}
}