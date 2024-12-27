package com.github.command17.enchantedbooklib.api.client.events.level.fabric;

import com.github.command17.enchantedbooklib.api.client.events.level.ClientLevelTickEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.jetbrains.annotations.ApiStatus;

@Environment(EnvType.CLIENT)
public final class ClientLevelTickEventImpl {
    private ClientLevelTickEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ClientTickEvents.START_WORLD_TICK.register((level) -> EventManager.invoke(new ClientLevelTickEvent.Pre(level)));
        ClientTickEvents.START_WORLD_TICK.register((level) -> EventManager.invoke(new ClientLevelTickEvent.Post(level)));
    }
}
