package com.github.command17.enchantedbooklib.api.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.google.common.collect.ArrayListMultimap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.function.Consumer;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class CreativeModeTabRegistryImpl {
    private CreativeModeTabRegistryImpl() {}

    private static final ArrayListMultimap<ResourceKey<CreativeModeTab>, Consumer<CreativeModeTab.Output>> OUTPUT_MODIFIER_MAP = ArrayListMultimap.create();

    public static CreativeModeTab.Builder createTabBuilder() {
        return CreativeModeTab.builder();
    }

    public static void modifyTabContent(ResourceKey<CreativeModeTab> tabKey, Consumer<CreativeModeTab.Output> outputModifier) {
        OUTPUT_MODIFIER_MAP.put(tabKey, outputModifier);
    }

    @SubscribeEvent
    private static void event(BuildCreativeModeTabContentsEvent event) {
        for (var outputModifier: OUTPUT_MODIFIER_MAP.get(event.getTabKey())) {
            outputModifier.accept(event);
        }
    }
}
