package com.github.command17.enchantedbooklib.api.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterWanderingTraderTradeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class RegisterWanderingTraderTradeEventImpl {
    private RegisterWanderingTraderTradeEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(WandererTradesEvent event) {
        EventManager.invoke(new RegisterWanderingTraderTradeEvent((rare, trade) -> {
            if (rare) {
                event.getRareTrades().add(trade);
            } else {
                event.getGenericTrades().add(trade);
            }
        }));
    }
}
