package com.github.command17.enchantedbooklib.api.client.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterItemColorProviderEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RegisterItemColorProviderEventImpl {
    private RegisterItemColorProviderEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(RegisterColorHandlersEvent.Item event) {
        EventManager.invoke(new RegisterItemColorProviderEvent(event::register));
    }
}
