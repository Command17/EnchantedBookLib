package com.github.command17.enchantedbooklib.api.client.events.entity.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.entity.ClientPlayerEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ClientPlayerEventImpl {
    private ClientPlayerEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ClientPlayerNetworkEvent.LoggingIn event) {
        EventManager.invoke(new ClientPlayerEvent.Join(event.getPlayer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ClientPlayerNetworkEvent.LoggingOut event) {
        EventManager.invoke(new ClientPlayerEvent.Quit(event.getPlayer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ClientPlayerNetworkEvent.Clone event) {
        EventManager.invoke(new ClientPlayerEvent.Respawn(event.getOldPlayer(), event.getNewPlayer()));
    }
}
