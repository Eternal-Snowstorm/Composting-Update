package dev.celestiacraft.composting.common.block.fermentation_box;

import dev.celestiacraft.composting.common.block.register.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FBBlock extends BaseEntityBlock {
	public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 7);
	public static final BooleanProperty READY = BooleanProperty.create("ready");

	public FBBlock() {
		super(Properties.copy(Blocks.COMPOSTER));
		registerDefaultState(stateDefinition.any()
				.setValue(READY, false)
				.setValue(LEVEL, 0));
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return ModBlockEntity.FERMENTATION_BOX.get().create(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LEVEL)
				.add(READY);
	}

	@Override
	public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		if (level.isClientSide()) {
			return null;
		}
		return createTickerHelper(
				type,
				ModBlockEntity.FERMENTATION_BOX.get(),
				FBBlockEntity::tick
		);
	}

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		BlockEntity be = level.getBlockEntity(pos);
		if (be instanceof FBBlockEntity entity) {
			return entity.use(player, hand);
		}

		return InteractionResult.PASS;
	}
}