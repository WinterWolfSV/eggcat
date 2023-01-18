package net.winterwolfsv.eggcat.block.custom;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.stream.Stream;


public class EggcatBlock extends HorizontalFacingBlock {
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public EggcatBlock(Settings settings) {
        super(FabricBlockSettings.of(Material.METAL).luminance((state) -> {
            return state.get(POWERED) ? 15 : 0;
        }));
        setDefaultState(getDefaultState().with(POWERED, false));
    }

    //@Override
    //public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
    //    Direction dir = state.get(FACING);
    //    switch (dir) {
    //        case NORTH:
    //            return Stream.of(
    //                    Block.createCuboidShape(10, 10, 7.5, 11, 12, 8.5),
    //                    Block.createCuboidShape(5, 10, 7.5, 6, 12, 8.5),
    //                    Block.createCuboidShape(6, 0, 6, 10, 11, 10),
    //                    Block.createCuboidShape(4, 1, 5, 5, 7, 11),
    //                    Block.createCuboidShape(5, 1, 4, 11, 7, 5),
    //                    Block.createCuboidShape(5, 1, 11, 11, 7, 12),
    //                    Block.createCuboidShape(11, 1, 5, 12, 7, 11),
    //                    Block.createCuboidShape(10, 1, 5, 11, 8, 6),
    //                    Block.createCuboidShape(5, 1, 5, 6, 8, 6),
    //                    Block.createCuboidShape(5, 1, 10, 6, 8, 11),
    //                    Block.createCuboidShape(10, 1, 10, 11, 8, 11),
    //                    Block.createCuboidShape(6, 0, 5, 10, 10, 6),
    //                    Block.createCuboidShape(6, 0, 10, 10, 10, 11),
    //                    Block.createCuboidShape(10, 0, 6, 11, 10, 10),
    //                    Block.createCuboidShape(5, 0, 6, 6, 10, 10),
    //                    Block.createCuboidShape(5.75, 11, 7.5, 6.75, 12, 8.5),
    //                    Block.createCuboidShape(9.25, 11, 7.5, 10.25, 12, 8.5)
    //            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    //        case SOUTH:
    //            Stream.of(
    //                    Block.createCuboidShape(5, 10, 7.5, 6, 12, 8.5),
    //                    Block.createCuboidShape(10, 10, 7.5, 11, 12, 8.5),
    //                    Block.createCuboidShape(6, 0, 6, 10, 11, 10),
    //                    Block.createCuboidShape(11, 1, 5, 12, 7, 11),
    //                    Block.createCuboidShape(5, 1, 11, 11, 7, 12),
    //                    Block.createCuboidShape(5, 1, 4, 11, 7, 5),
    //                    Block.createCuboidShape(4, 1, 5, 5, 7, 11),
    //                    Block.createCuboidShape(5, 1, 10, 6, 8, 11),
    //                    Block.createCuboidShape(10, 1, 10, 11, 8, 11),
    //                    Block.createCuboidShape(10, 1, 5, 11, 8, 6),
    //                    Block.createCuboidShape(5, 1, 5, 6, 8, 6),
    //                    Block.createCuboidShape(6, 0, 10, 10, 10, 11),
    //                    Block.createCuboidShape(6, 0, 5, 10, 10, 6),
    //                    Block.createCuboidShape(5, 0, 6, 6, 10, 10),
    //                    Block.createCuboidShape(10, 0, 6, 11, 10, 10),
    //                    Block.createCuboidShape(9.25, 11, 7.5, 10.25, 12, 8.5),
    //                    Block.createCuboidShape(5.75, 11, 7.5, 6.75, 12, 8.5)
    //            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    //        case EAST:
    //            return Stream.of(
    //                    Block.createCuboidShape(7.5, 10, 10, 8.5, 12, 11),
    //                    Block.createCuboidShape(7.5, 10, 5, 8.5, 12, 6),
    //                    Block.createCuboidShape(6, 0, 6, 10, 11, 10),
    //                    Block.createCuboidShape(5, 1, 4, 11, 7, 5),
    //                    Block.createCuboidShape(11, 1, 5, 12, 7, 11),
    //                    Block.createCuboidShape(4, 1, 5, 5, 7, 11),
    //                    Block.createCuboidShape(5, 1, 11, 11, 7, 12),
    //                    Block.createCuboidShape(10, 1, 10, 11, 8, 11),
    //                    Block.createCuboidShape(10, 1, 5, 11, 8, 6),
    //                    Block.createCuboidShape(5, 1, 5, 6, 8, 6),
    //                    Block.createCuboidShape(5, 1, 10, 6, 8, 11),
    //                    Block.createCuboidShape(10, 0, 6, 11, 10, 10),
    //                    Block.createCuboidShape(5, 0, 6, 6, 10, 10),
    //                    Block.createCuboidShape(6, 0, 10, 10, 10, 11),
    //                    Block.createCuboidShape(6, 0, 5, 10, 10, 6),
    //                    Block.createCuboidShape(7.5, 11, 5.75, 8.5, 12, 6.75),
    //                    Block.createCuboidShape(7.5, 11, 9.25, 8.5, 12, 10.25)
    //            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    //        case WEST:
    //            return Stream.of(
    //                    Block.createCuboidShape(7.5, 10, 5, 8.5, 12, 6),
    //                    Block.createCuboidShape(7.5, 10, 10, 8.5, 12, 11),
    //                    Block.createCuboidShape(6, 0, 6, 10, 11, 10),
    //                    Block.createCuboidShape(5, 1, 11, 11, 7, 12),
    //                    Block.createCuboidShape(4, 1, 5, 5, 7, 11),
    //                    Block.createCuboidShape(11, 1, 5, 12, 7, 11),
    //                    Block.createCuboidShape(5, 1, 4, 11, 7, 5),
    //                    Block.createCuboidShape(5, 1, 5, 6, 8, 6),
    //                    Block.createCuboidShape(5, 1, 10, 6, 8, 11),
    //                    Block.createCuboidShape(10, 1, 10, 11, 8, 11),
    //                    Block.createCuboidShape(10, 1, 5, 11, 8, 6),
    //                    Block.createCuboidShape(5, 0, 6, 6, 10, 10),
    //                    Block.createCuboidShape(10, 0, 6, 11, 10, 10),
    //                    Block.createCuboidShape(6, 0, 5, 10, 10, 6),
    //                    Block.createCuboidShape(6, 0, 10, 10, 10, 11),
    //                    Block.createCuboidShape(7.5, 11, 9.25, 8.5, 12, 10.25),
    //                    Block.createCuboidShape(7.5, 11, 5.75, 8.5, 12, 6.75)
    //            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    //        default:
    //            return VoxelShapes.fullCube();
    //    }
//
    //}

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SkullBlockEntity skullBlockEntity) {
            GameProfile gameProfile = null;
            if (itemStack.hasNbt()) {
                NbtCompound nbtCompound = itemStack.getNbt();
                if (nbtCompound.contains("SkullOwner", 10)) {
                    gameProfile = NbtHelper.toGameProfile(nbtCompound.getCompound("SkullOwner"));
                } else if (nbtCompound.contains("SkullOwner", 8) && !StringUtils.isBlank(nbtCompound.getString("SkullOwner"))) {
                    gameProfile = new GameProfile((UUID) null, nbtCompound.getString("SkullOwner"));
                }
            }

            skullBlockEntity.setOwner(gameProfile);
        }

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
        world.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6f);
        return ActionResult.SUCCESS;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }
}
