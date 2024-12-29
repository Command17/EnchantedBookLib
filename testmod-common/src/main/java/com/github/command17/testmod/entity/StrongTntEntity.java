package com.github.command17.testmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.portal.DimensionTransition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class StrongTntEntity extends Entity implements TraceableEntity {
    public static final String TAG_FUSE = "fuse";

    private static final int DEFAULT_FUSE_TIME = 80;
    private static final String TAG_BLOCK_STATE = "block_state";
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(StrongTntEntity.class, EntityDataSerializers.INT);;
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE_ID = SynchedEntityData.defineId(StrongTntEntity.class, EntityDataSerializers.BLOCK_STATE);;
    private static final ExplosionDamageCalculator USED_PORTAL_DAMAGE_CALCULATOR = new ExplosionDamageCalculator() {
        public boolean shouldBlockExplode(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, float power) {
            return !state.is(Blocks.NETHER_PORTAL) && super.shouldBlockExplode(explosion, reader, pos, state, power);
        }

        @NotNull
        public Optional<Float> getBlockExplosionResistance(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, FluidState fluid) {
            return state.is(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlockExplosionResistance(explosion, reader, pos, state, fluid);
        }
    };

    private boolean usedPortal;

    @Nullable
    private LivingEntity owner;

    public StrongTntEntity(EntityType<StrongTntEntity> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public StrongTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner) {
        this(ModEntities.STRONG_TNT.get(), level);
        this.setPos(x, y, z);
        double offset = level.random.nextDouble() * (Math.PI * 2);
        this.setDeltaMovement(-Math.sin(offset) * 0.02, 0.2f, -Math.cos(offset) * 0.02);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = owner;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_FUSE_ID, 80);
        builder.define(DATA_BLOCK_STATE_ID, Blocks.TNT.defaultBlockState());
    }

    @NotNull
    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.04;
    }

    @Override
    public void tick() {
        this.handlePortal();
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));

        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5f, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);

        if (i <= 0) {
            this.discard();

            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();

            if (this.level().isClientSide) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5f, this.getZ(), 0, 0, 0);
            }
        }
    }

    private void explode() {
        this.level().explode(this, Explosion.getDefaultDamageSource(this.level(), this), this.usedPortal ? USED_PORTAL_DAMAGE_CALCULATOR : null, this.getX(), this.getY(0.0625f), this.getZ(), 8, false, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putShort("fuse", (short)this.getFuse());
        compound.put("block_state", NbtUtils.writeBlockState(this.getBlockState()));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setFuse(compound.getShort("fuse"));

        if (compound.contains("block_state", 10)) {
            this.setBlockState(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("block_state")));
        }

    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return this.owner;
    }

    public void restoreFrom(Entity entity) {
        super.restoreFrom(entity);

        if (entity instanceof StrongTntEntity tntEntity) {
            this.owner = tntEntity.owner;
        }
    }

    public void setFuse(int fuse) {
        this.entityData.set(DATA_FUSE_ID, fuse);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public void setBlockState(BlockState blockState) {
        this.entityData.set(DATA_BLOCK_STATE_ID, blockState);
    }

    public BlockState getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE_ID);
    }

    private void setUsedPortal(boolean usedPortal) {
        this.usedPortal = usedPortal;
    }

    @Nullable
    public Entity changeDimension(DimensionTransition transition) {
        Entity entity = super.changeDimension(transition);

        if (entity instanceof StrongTntEntity tntEntity) {
            tntEntity.setUsedPortal(true);
        }

        return entity;
    }
}
