package com.github.command17.enchantedbooklib.api.events.level;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class BlockEvent extends Event {
    private final Level level;
    private final BlockPos pos;
    private final BlockState state;

    public BlockEvent(Level level, BlockPos pos, BlockState state) {
        this.level = level;
        this.pos = pos;
        this.state = state;
    }

    public Level getLevel() {
        return level;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    /**
     * Event Details: <b>Can be canceled</b> and <b>Server only event<b/>
     */
    public static class Place extends BlockEvent implements ICancelableEvent {
        @Nullable
        private final Entity placer;

        public Place(Level level, BlockPos pos, BlockState state, @Nullable Entity placer) {
            super(level, pos, state);
            this.placer = placer;
        }

        @Nullable
        public Entity getPlacer() {
            return placer;
        }
    }

    /**
     * Event Details: <b>Can be canceled</b> and <b>Server only event<b/>
     */
public static class Break extends BlockEvent implements ICancelableEvent {
        private final ServerPlayer player;

        public Break(Level level, BlockPos pos, BlockState state, ServerPlayer player) {
            super(level, pos, state);
            this.player = player;
        }

        public ServerPlayer getPlayer() {
            return player;
        }
    }
}
