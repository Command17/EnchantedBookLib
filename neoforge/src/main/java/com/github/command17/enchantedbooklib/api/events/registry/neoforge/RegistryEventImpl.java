package com.github.command17.enchantedbooklib.api.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.registry.RegistryEvent;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class RegistryEventImpl {
    private RegistryEventImpl() {}

    @SuppressWarnings("unchecked")
    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(RegisterEvent event) {
        var registryKey = event.getRegistryKey();
        EventManager.invoke(new RegistryEvent(registryKey, event.getRegistry(),
                (id, entry) -> event.register((ResourceKey<? extends Registry<Object>>) registryKey, id, (Supplier<Object>) entry)));
    }
}
