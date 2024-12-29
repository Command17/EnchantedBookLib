package com.github.command17.enchantedbooklib.api.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterEntityAttributesEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class RegisterEntityAttributesEventImpl {
    private RegisterEntityAttributesEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(EntityAttributeCreationEvent event) {
        EventManager.invoke(new RegisterEntityAttributesEvent(event::put));
    }
}
