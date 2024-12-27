package com.github.command17.enchantedbooklib.api.events.entity;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public abstract class PlayerEvent extends Event {
    private final Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public static class Join extends PlayerEvent {
        public Join(ServerPlayer player) {
            super(player);
        }

        @Override
        public ServerPlayer getPlayer() {
            return (ServerPlayer) super.getPlayer();
        }
    }

    public static class Quit extends PlayerEvent {
        public Quit(ServerPlayer player) {
            super(player);
        }

        @Override
        public ServerPlayer getPlayer() {
            return (ServerPlayer) super.getPlayer();
        }
    }

    public static class Respawn extends PlayerEvent {
        private final Entity.RemovalReason reason;

        public Respawn(ServerPlayer player, Entity.RemovalReason reason) {
            super(player);
            this.reason = reason;
        }

        public Entity.RemovalReason getReason() {
            return reason;
        }
    }

    public static class PickUpItem extends PlayerEvent {
        private final ItemEntity entity;
        private final ItemStack stack;

        public PickUpItem(Player player, ItemEntity entity, ItemStack stack) {
            super(player);
            this.entity = entity;
            this.stack = stack;
        }

        public ItemEntity getEntity() {
            return entity;
        }

        public ItemStack getStack() {
            return stack;
        }
    }

    public static class BeforePickUpItem extends PickUpItem implements ICancelableEvent {
        private boolean canPickUp = true;

        public BeforePickUpItem(Player player, ItemEntity entity, ItemStack stack) {
            super(player, entity, stack);
        }

        public boolean canPickUp() {
            return canPickUp;
        }

        public void setCanPickUp(boolean canPickUp) {
            this.canPickUp = canPickUp;
        }
    }

    public static class DropItem extends PlayerEvent implements ICancelableEvent {
        private final ItemEntity entity;

        public DropItem(Player player, ItemEntity entity) {
            super(player);
            this.entity = entity;
        }

        public ItemEntity getEntity() {
            return entity;
        }
    }

    public static class AttackEntity extends PlayerEvent implements ICancelableEvent {
        private final Level level;
        private final Entity target;
        private final InteractionHand hand;

        @Nullable
        private final EntityHitResult entityHitResult;

        public AttackEntity(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult entityHitResult) {
            super(player);
            this.level = level;
            this.target = target;
            this.hand = hand;
            this.entityHitResult = entityHitResult;
        }

        public Level getLevel() {
            return level;
        }

        public Entity getTarget() {
            return target;
        }

        public InteractionHand getHand() {
            return hand;
        }

        @Nullable
        public EntityHitResult getEntityHitResult() {
            return entityHitResult;
        }
    }

    public static class PreTick extends PlayerEvent {
        public PreTick(Player player) {
            super(player);
        }
    }

    public static class PostTick extends PlayerEvent {
        public PostTick(Player player) {
            super(player);
        }
    }
}
