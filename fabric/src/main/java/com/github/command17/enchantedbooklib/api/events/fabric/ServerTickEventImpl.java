package com.github.command17.enchantedbooklib.api.events.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.ServerTickEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.jetbrains.annotations.ApiStatus;

public final class ServerTickEventImpl {
    private ServerTickEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ServerTickEvents.START_SERVER_TICK.register((server) -> EventManager.invoke(new ServerTickEvent.Pre(server)));
        ServerTickEvents.END_SERVER_TICK.register((server) -> EventManager.invoke(new ServerTickEvent.Post(server)));
    }
}
