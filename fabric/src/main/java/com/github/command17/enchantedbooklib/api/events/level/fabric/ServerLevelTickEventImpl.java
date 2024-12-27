package com.github.command17.enchantedbooklib.api.events.level.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.level.ServerLevelTickEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.jetbrains.annotations.ApiStatus;

public final class ServerLevelTickEventImpl {
    private ServerLevelTickEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ServerTickEvents.START_WORLD_TICK.register((level) -> EventManager.invoke(new ServerLevelTickEvent.Pre(level)));
        ServerTickEvents.END_WORLD_TICK.register((level) -> EventManager.invoke(new ServerLevelTickEvent.Post(level)));
    }
}
