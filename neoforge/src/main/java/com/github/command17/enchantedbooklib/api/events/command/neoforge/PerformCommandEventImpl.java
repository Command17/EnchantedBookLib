package com.github.command17.enchantedbooklib.api.events.command.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.command.PerformCommandEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.CommandEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class PerformCommandEventImpl {
    private PerformCommandEventImpl() {}

    @SubscribeEvent
    private static void event(CommandEvent event) {
        PerformCommandEvent performCommandEvent = new PerformCommandEvent(event.getParseResults(), event.getException());
        EventManager.invoke(performCommandEvent);
        event.setCanceled(performCommandEvent.isCanceled());
        event.setException(performCommandEvent.getThrowable());
        event.setParseResults(performCommandEvent.getResults());
    }
}
