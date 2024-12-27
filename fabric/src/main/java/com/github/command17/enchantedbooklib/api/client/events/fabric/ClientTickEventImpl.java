package com.github.command17.enchantedbooklib.api.client.events.fabric;

import com.github.command17.enchantedbooklib.api.client.events.ClientTickEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.jetbrains.annotations.ApiStatus;

@Environment(EnvType.CLIENT)
public final class ClientTickEventImpl {
    private ClientTickEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ClientTickEvents.START_CLIENT_TICK.register((client) -> EventManager.invoke(new ClientTickEvent.Pre(client)));
        ClientTickEvents.END_CLIENT_TICK.register((client) -> EventManager.invoke(new ClientTickEvent.Post(client)));
    }
}
