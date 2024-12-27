package com.github.command17.enchantedbooklib.api.registry.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public final class CreativeModeTabRegistryImpl {
    private CreativeModeTabRegistryImpl() {}

    public static CreativeModeTab.Builder createTabBuilder() {
        return FabricItemGroup.builder();
    }

    public static void modifyTabContent(ResourceKey<CreativeModeTab> tabKey, Consumer<CreativeModeTab.Output> outputModifier) {
        ItemGroupEvents.modifyEntriesEvent(tabKey).register(outputModifier::accept);
    }
}
