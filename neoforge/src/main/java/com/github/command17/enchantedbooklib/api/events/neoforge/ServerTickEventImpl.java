package com.github.command17.enchantedbooklib.api.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class ServerTickEventImpl {
    private ServerTickEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ServerTickEvent.Pre event) {
        EventManager.invoke(new com.github.command17.enchantedbooklib.api.events.ServerTickEvent.Pre(event.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ServerTickEvent.Post event) {
        EventManager.invoke(new com.github.command17.enchantedbooklib.api.events.ServerTickEvent.Post(event.getServer()));
    }
}
