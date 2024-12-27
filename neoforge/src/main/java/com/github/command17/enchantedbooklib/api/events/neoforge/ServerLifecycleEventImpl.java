package com.github.command17.enchantedbooklib.api.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.ServerLifecycleEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class ServerLifecycleEventImpl {
    private ServerLifecycleEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ServerStartingEvent event) {
        EventManager.invoke(new ServerLifecycleEvent.Starting(event.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ServerStartedEvent event) {
        EventManager.invoke(new ServerLifecycleEvent.Started(event.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ServerStoppingEvent event) {
        EventManager.invoke(new ServerLifecycleEvent.Stopping(event.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ServerStoppedEvent event) {
        EventManager.invoke(new ServerLifecycleEvent.Stopped(event.getServer()));
    }
}
