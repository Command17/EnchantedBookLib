package com.github.command17.enchantedbooklib.api.client.events.entity;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public abstract class ClientPlayerEvent extends Event {
    private final LocalPlayer player;

    public ClientPlayerEvent(LocalPlayer player) {
        this.player = player;
    }

    public LocalPlayer getPlayer() {
        return player;
    }

    public static class Join extends ClientPlayerEvent {
        public Join(LocalPlayer player) {
            super(player);
        }
    }

    public static class Quit extends ClientPlayerEvent {
        public Quit(@Nullable LocalPlayer player) {
            super(player);
        }

        @Nullable
        @Override
        public LocalPlayer getPlayer() {
            return super.getPlayer();
        }
    }

    public static class Respawn extends ClientPlayerEvent {
        private final LocalPlayer newPlayer;

        public Respawn(LocalPlayer oldPlayer, LocalPlayer newPlayer) {
            super(oldPlayer);
            this.newPlayer = newPlayer;
        }

        public LocalPlayer getOldPlayer() {
            return this.getPlayer();
        }

        public LocalPlayer getNewPlayer() {
            return newPlayer;
        }
    }
}
