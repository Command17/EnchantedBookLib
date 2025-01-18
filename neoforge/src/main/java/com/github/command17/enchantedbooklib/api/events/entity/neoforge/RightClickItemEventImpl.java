package com.github.command17.enchantedbooklib.api.events.entity.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.RightClickItemEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class RightClickItemEventImpl {
    private RightClickItemEventImpl() {}

    @SubscribeEvent
    private static void event(PlayerInteractEvent.RightClickItem event) {
        RightClickItemEvent rightClickItemEvent = new RightClickItemEvent(event.getEntity(), event.getHand());
        EventManager.invoke(rightClickItemEvent);

        if (rightClickItemEvent.isCanceled()) {
            event.setCancellationResult(rightClickItemEvent.getResult());
            event.setCanceled(true);
        }
    }
}
