package dev.celestiacraft.composting.common.block.fermentation_box;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.celestiacraft.composting.Composting;
import dev.celestiacraft.composting.CompostingConfig;
import dev.celestiacraft.composting.common.block.register.ModBlockEntity;
import dev.celestiacraft.composting.common.block.register.ModRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class FBBlockEntity extends BlockEntity {
	private int progress = 0;

	public FBBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntity.FERMENTATION_BOX.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, FBBlockEntity entity) {
		if (state.getValue(FBBlock.LEVEL) >= 7) {
			entity.progress++;
			entity.setChanged();

			if (entity.progress >= getProgressTime()) {
				level.setBlockAndUpdate(pos, state.setValue(FBBlock.READY, true));
			}
		} else {
			if (entity.progress != 0) {
				entity.progress = 0;
				entity.setChanged();
			}
		}
	}

	private static int getProgressTime() {
		return CompostingConfig.PROGRESS_TIME.get();
	}

	public InteractionResult use(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		player.swing(hand);

		if (getBlockState().getValue(FBBlock.READY)) {
			player.playNotifySound(
					SoundEvents.ITEM_PICKUP,
					SoundSource.BLOCKS,
					1,
					1
			);
			return harvest(player);
		}

		FBBRecipe recipe = fineRecipe(stack);

		if (recipe == null) {
			return InteractionResult.PASS;
		}

		return insert(player, stack, recipe);
	}

	private InteractionResult harvest(Player player) {
		ResourceLocation id = ResourceLocation.tryParse(CompostingConfig.RESULT_ITEM.get());

		Item item = ForgeRegistries.ITEMS.getValue(id);
		if (item == null || item == Items.AIR) {
			item = Items.BONE_MEAL;
		}

		ItemStack result = new ItemStack(item, CompostingConfig.RESULT_COUNT.get());

		String snbt = CompostingConfig.RESULT_NBT.get();
		if (!snbt.isBlank() && !"{}".equals(snbt)) {
			try {
				result.setTag(TagParser.parseTag(snbt));
			} catch (CommandSyntaxException exception) {
				Composting.LOGGER.error("Invalid fermentation result NBT: {}", snbt, exception);
			}
		}

		if (!player.addItem(result)) {
			player.drop(result, false);
		}

		progress = 0;

		level.setBlockAndUpdate(
				worldPosition,
				getBlockState()
						.setValue(FBBlock.LEVEL, 0)
						.setValue(FBBlock.READY, false)
		);

		setChanged();

		return InteractionResult.CONSUME;
	}

	private InteractionResult insert(Player player, ItemStack stack, FBBRecipe recipe) {
		BlockState state = getBlockState();

		if (state.getValue(FBBlock.LEVEL) >= 7) {
			return InteractionResult.CONSUME;
		}

		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
		}

		if (recipe.roll(level.random)) {
			BlockState newState = state.setValue(
					FBBlock.LEVEL,
					state.getValue(FBBlock.LEVEL) + 1
			);

			player.playNotifySound(
					SoundEvents.GRAVEL_BREAK,
					SoundSource.BLOCKS,
					1,
					1
			);
			level.setBlockAndUpdate(worldPosition, newState);
		}

		return InteractionResult.CONSUME;
	}

	private FBBRecipe fineRecipe(ItemStack stack) {
		if (level == null) {
			return null;
		}

		return level.getRecipeManager()
				.getAllRecipesFor(ModRecipeType.FERMENTATION_BOX.get())
				.stream()
				.filter((recipe) -> {
					return recipe.getIngredient().test(stack);
				})
				.findFirst()
				.orElse(null);
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("Progress", progress);
	}

	@Override
	public void load(@NotNull CompoundTag tag) {
		super.load(tag);
		progress = tag.getInt("Progress");
	}
}