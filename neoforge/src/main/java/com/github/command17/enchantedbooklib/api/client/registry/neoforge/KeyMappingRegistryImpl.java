package com.github.command17.enchantedbooklib.api.client.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class KeyMappingRegistryImpl {
    private KeyMappingRegistryImpl() {}

    private static final ArrayList<Supplier<KeyMapping>> KEY_MAPPING_LIST = new ArrayList<>();

    public static Supplier<KeyMapping> registerKeyMapping(Supplier<KeyMapping> keyMapping) {
        KEY_MAPPING_LIST.add(keyMapping);
        return keyMapping;
    }

    @SubscribeEvent
    private static void event(RegisterKeyMappingsEvent event) {
        KEY_MAPPING_LIST.forEach((keyMapping) -> event.register(keyMapping.get()));
    }
}
