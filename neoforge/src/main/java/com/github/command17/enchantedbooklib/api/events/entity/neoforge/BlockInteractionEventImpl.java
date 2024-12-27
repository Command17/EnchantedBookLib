package com.github.command17.enchantedbooklib.api.events.entity.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.BlockInteractionEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class BlockInteractionEventImpl {
    private BlockInteractionEventImpl() {}

    @SubscribeEvent
    private static void event(PlayerInteractEvent.RightClickBlock event) {
        BlockInteractionEvent.RightClick rightClickEvent = new BlockInteractionEvent.RightClick(event.getEntity(), event.getHand(), event.getPos(), event.getFace());
        EventManager.invoke(rightClickEvent);
        event.setCanceled(rightClickEvent.isCanceled());
    }

    @SubscribeEvent
    private static void event(PlayerInteractEvent.LeftClickBlock event) {
        BlockInteractionEvent.LeftClick leftClickEvent = new BlockInteractionEvent.LeftClick(event.getEntity(), event.getHand(), event.getPos(), event.getFace());
        EventManager.invoke(leftClickEvent);
        event.setCanceled(leftClickEvent.isCanceled());
    }
}
