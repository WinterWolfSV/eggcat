package net.winterwolfsv.eggcat.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Objects;


public class EggcatBlock extends HorizontalFacingBlock {
    protected static final VoxelShape MAIN_SHAPE;
    protected static final VoxelShape NORTH_SOUTH_SHAPE;
    protected static final VoxelShape EAST_WEST_SHAPE;
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public EggcatBlock(Settings settings) {
        super(settings.luminance((state) -> state.get(POWERED) ? 15 : 0));
        setDefaultState(getDefaultState().with(POWERED, false));
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH, SOUTH -> NORTH_SOUTH_SHAPE;
            case EAST, WEST -> EAST_WEST_SHAPE;
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, true));
            AbstractBlock.Settings.of(this.material).luminance(l -> 100);
        } else {
            world.setBlockState(pos, state.with(POWERED, false));
            AbstractBlock.Settings.of(this.material).luminance(l -> 0);
        }
        world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6f);
        return ActionResult.SUCCESS;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    static {
        MAIN_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(6, 0, 6, 10, 11, 10),
                Block.createCuboidShape(4, 1, 5, 5, 7, 11),
                Block.createCuboidShape(5, 1, 4, 11, 7, 5),
                Block.createCuboidShape(5, 1, 11, 11, 7, 12),
                Block.createCuboidShape(11, 1, 5, 12, 7, 11),
                Block.createCuboidShape(10, 1, 5, 11, 8, 6),
                Block.createCuboidShape(5, 1, 5, 6, 8, 6),
                Block.createCuboidShape(5, 1, 10, 6, 8, 11),
                Block.createCuboidShape(10, 1, 10, 11, 8, 11),
                Block.createCuboidShape(6, 0, 5, 10, 10, 6),
                Block.createCuboidShape(6, 0, 10, 10, 10, 11),
                Block.createCuboidShape(10, 0, 6, 11, 10, 10),
                Block.createCuboidShape(5, 0, 6, 6, 10, 10)
        );
        NORTH_SOUTH_SHAPE = VoxelShapes.union(MAIN_SHAPE,
                Block.createCuboidShape(9, 10, 7.5, 11, 12, 8.5),
                Block.createCuboidShape(5, 10, 7.5, 7, 12, 8.5)
        );
        EAST_WEST_SHAPE = VoxelShapes.union(MAIN_SHAPE,
                Block.createCuboidShape(7.5, 10, 9, 8.5, 12, 11),
                Block.createCuboidShape(7.5, 10, 5, 8.5, 12, 7));
    }
}
