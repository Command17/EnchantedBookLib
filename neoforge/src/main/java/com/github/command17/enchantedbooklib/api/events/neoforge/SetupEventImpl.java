package com.github.command17.enchantedbooklib.api.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.SetupEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class SetupEventImpl {
    private SetupEventImpl() {}

    @SubscribeEvent
    private static void event(FMLCommonSetupEvent event) {
        EventManager.invoke(new SetupEvent());
    }
}
