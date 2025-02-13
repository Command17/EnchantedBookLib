package com.github.command17.testmod.block;

import com.github.command17.testmod.entity.StrongTntEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StrongTntBlock extends Block {
    public static final MapCodec<StrongTntBlock> CODEC = simpleCodec(StrongTntBlock::new);
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public StrongTntBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @NotNull
    public MapCodec<StrongTntBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            if (level.hasNeighborSignal(pos)) {
                explode(level, pos);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (level.hasNeighborSignal(pos)) {
            explode(level, pos);
            level.removeBlock(pos, false);
        }
    }

    @NotNull
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && !player.isCreative() && state.getValue(UNSTABLE)) {
            explode(level, pos);
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            StrongTntEntity tntEntity = new StrongTntEntity(level, pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f, explosion.getIndirectSourceEntity());
            int i = tntEntity.getFuse();
            tntEntity.setFuse(level.random.nextInt(i / 4) + i / 8);
            level.addFreshEntity(tntEntity);
        }
    }

    public static void explode(Level level, BlockPos pos) {
        explode(level, pos, null);
    }

    private static void explode(Level level, BlockPos pos, @Nullable LivingEntity entity) {
        if (!level.isClientSide) {
            StrongTntEntity tntEntity = new StrongTntEntity(level, pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f, entity);
            level.addFreshEntity(tntEntity);
            level.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1, 1);
            level.gameEvent(entity, GameEvent.PRIME_FUSE, pos);
        }
    }

    @NotNull
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            explode(level, pos, player);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            Item item = stack.getItem();

            if (stack.is(Items.FLINT_AND_STEEL)) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            } else {
                stack.consume(1, player);
            }

            player.awardStat(Stats.ITEM_USED.get(item));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos blockPos = hit.getBlockPos();
            Entity entity = projectile.getOwner();

            if (projectile.isOnFire() && projectile.mayInteract(level, blockPos)) {
                explode(level, blockPos, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                level.removeBlock(blockPos, false);
            }
        }

    }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }
}
