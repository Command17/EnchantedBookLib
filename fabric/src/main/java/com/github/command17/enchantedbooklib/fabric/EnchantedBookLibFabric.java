package com.github.command17.enchantedbooklib.fabric;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.entrypoint.fabric.EnchantedModInitializer;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.SetupEvent;
import com.github.command17.enchantedbooklib.api.events.command.fabric.RegisterCommandEventImpl;
import com.github.command17.enchantedbooklib.api.events.entity.fabric.BlockInteractionEventImpl;
import com.github.command17.enchantedbooklib.api.events.entity.fabric.PlayerEventImpl;
import com.github.command17.enchantedbooklib.api.events.entity.fabric.RightClickItemEventImpl;
import com.github.command17.enchantedbooklib.api.events.fabric.ModifyLootTableEventImpl;
import com.github.command17.enchantedbooklib.api.events.fabric.ServerLifecycleEventImpl;
import com.github.command17.enchantedbooklib.api.events.fabric.ServerTickEventImpl;
import com.github.command17.enchantedbooklib.api.events.level.fabric.ServerLevelTickEventImpl;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterEntityAttributesEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterFuelEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterVillagerTradeEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegistryEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class EnchantedBookLibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Bind Events
        ServerLifecycleEventImpl.register();
        ServerTickEventImpl.register();
        ModifyLootTableEventImpl.register();
        ServerLevelTickEventImpl.register();
        RightClickItemEventImpl.register();
        BlockInteractionEventImpl.register();
        PlayerEventImpl.register();
        RegisterCommandEventImpl.register();

        // Init main
        EnchantedBookLib.init();

        // Initialize custom entrypoint
        FabricLoader.getInstance().getEntrypoints("enchanted-main", EnchantedModInitializer.class)
                .forEach(EnchantedModInitializer::onInitialize);

        invokeRegistryEvents();
        invokePostRegisterEvents();
        EventManager.invoke(new SetupEvent());
    }

    @SuppressWarnings("unchecked")
    private static void invokeRegistryEvents() {
        for (ResourceLocation registryName : BuiltInRegistries.LOADERS.keySet()) {
            ResourceKey<? extends Registry<?>> registryKey = ResourceKey.createRegistryKey(registryName);
            Registry<?> registry = BuiltInRegistries.REGISTRY.get(registryName);
            EventManager.invoke(new RegistryEvent(registryKey, registry, (id, entry) -> Registry.register((Registry<? super Object>) registry, id, entry.get())));
        }
    }

    private static void invokePostRegisterEvents() {
        EventManager.invoke(new RegisterFuelEvent());
        EventManager.invoke(new RegisterEntityAttributesEvent());
        EventManager.invoke(new RegisterVillagerTradeEvent.VillagerTrade());
        EventManager.invoke(new RegisterVillagerTradeEvent.WanderingTraderTrade());
    }
}
