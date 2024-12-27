package com.github.command17.enchantedbooklib.api.events.level.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.level.BlockEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent.BreakEvent;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class BlockEventImpl {
    private BlockEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(BreakEvent event) {
        BlockEvent.Break breakEvent = new BlockEvent.Break((Level) event.getLevel(), event.getPos(), event.getState(), (ServerPlayer) event.getPlayer());
        EventManager.invoke(breakEvent);
        event.setCanceled(breakEvent.isCanceled());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(EntityPlaceEvent event) {
        BlockEvent.Place placeEvent = new BlockEvent.Place((Level) event.getLevel(), event.getPos(), event.getState(), event.getEntity());
        EventManager.invoke(placeEvent);
        event.setCanceled(placeEvent.isCanceled());
    }
}
