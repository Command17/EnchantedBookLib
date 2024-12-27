package com.github.command17.enchantedbooklib.api.events.entity;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

public abstract class BlockInteractionEvent extends Event {
    private final Player player;
    private final InteractionHand hand;
    private final BlockPos pos;
    private final Direction direction;

    public BlockInteractionEvent(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        this.player = player;
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    public Player getPlayer() {
        return player;
    }

    public InteractionHand getHand() {
        return hand;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Direction getDirection() {
        return direction;
    }

    public static class RightClick extends BlockInteractionEvent implements ICancelableEvent {
        public RightClick(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
            super(player, hand, pos, direction);
        }
    }

    public static class LeftClick extends BlockInteractionEvent implements ICancelableEvent {
        public LeftClick(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
            super(player, hand, pos, direction);
        }
    }
}
