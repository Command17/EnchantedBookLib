package com.github.command17.enchantedbooklib.api.client.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterEntityLayerEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RegisterEntityLayerEventImpl {
    private RegisterEntityLayerEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(EntityRenderersEvent.RegisterLayerDefinitions event) {
        EventManager.invoke(new RegisterEntityLayerEvent(event::registerLayerDefinition));
    }
}
