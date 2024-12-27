package com.github.command17.enchantedbooklib.api.hooks;

import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.events.ServerLifecycleEvent;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class MinecraftServerHooks {
    private MinecraftServerHooks() {}

    @Nullable
    private static MinecraftServer server = null;

    public static Optional<MinecraftServer> getServer() {
        return Optional.ofNullable(server);
    }

    @EventListener
    private static void event(ServerLifecycleEvent.Starting event) {
        server = event.getServer();
    }

    @EventListener
    private static void event(ServerLifecycleEvent.Stopped event) {
        server = null;
    }
}
