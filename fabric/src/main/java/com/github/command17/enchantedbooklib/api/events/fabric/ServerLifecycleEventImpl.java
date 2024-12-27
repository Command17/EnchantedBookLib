package com.github.command17.enchantedbooklib.api.events.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.ServerLifecycleEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.jetbrains.annotations.ApiStatus;

public final class ServerLifecycleEventImpl {
    private ServerLifecycleEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ServerLifecycleEvents.SERVER_STARTING.register((server) -> EventManager.invoke(new ServerLifecycleEvent.Starting(server)));
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> EventManager.invoke(new ServerLifecycleEvent.Started(server)));
        ServerLifecycleEvents.SERVER_STOPPING.register((server) -> EventManager.invoke(new ServerLifecycleEvent.Starting(server)));
        ServerLifecycleEvents.SERVER_STOPPED.register((server) -> EventManager.invoke(new ServerLifecycleEvent.Stopped(server)));
    }
}
