package com.github.command17.enchantedbooklib.api.client.events.fabric;

import com.github.command17.enchantedbooklib.api.client.events.ClientLifecycleEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import org.jetbrains.annotations.ApiStatus;

@Environment(EnvType.CLIENT)
public final class ClientLifecycleEventImpl {
    private ClientLifecycleEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> EventManager.invoke(new ClientLifecycleEvent.Started(client)));
        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> EventManager.invoke(new ClientLifecycleEvent.Stopped(client)));
    }
}
