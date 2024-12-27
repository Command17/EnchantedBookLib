package com.github.command17.enchantedbooklib.api.hooks.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;

import java.util.Optional;

public final class EventBusHooks {
    private EventBusHooks() {}

    public static Optional<IEventBus> getModEventBus(String modId) {
        return ModList.get().getModContainerById(modId).map(ModContainer::getEventBus);
    }
}
