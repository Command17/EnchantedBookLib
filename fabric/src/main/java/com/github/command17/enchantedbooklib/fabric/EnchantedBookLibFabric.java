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
import com.github.command17.enchantedbooklib.api.events.registry.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

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
            Registry<?> registry = BuiltInRegistries.REGISTRY.getValue(registryName);
            EventManager.invoke(new RegistryEvent(registryKey, registry, (id, entry) -> Registry.register((Registry<? super Object>) registry, id, entry.get())));
        }
    }

    private static void invokeRegisterFuelEvent() {
        Int2ObjectArrayMap<ItemLike> fuelMap = new Int2ObjectArrayMap<>();
        EventManager.invoke(new RegisterFuelEvent((time, item) -> fuelMap.put((int) time, item)));

        FuelRegistryEvents.BUILD.register(
                (builder, context) -> fuelMap.forEach((time, item) -> {
                    if (time > 0) {
                        builder.add(item, time);
                    }
                })
        );

        FuelRegistryEvents.EXCLUSIONS.register(
                (builder, context) -> fuelMap.forEach((time, item) -> {
                    if (time <= 0) {
                        builder.values.keySet().remove(item);
                    }
                })
        );
    }

    private static void invokePostRegisterEvents() {
        invokeRegisterFuelEvent();
        EventManager.invoke(new RegisterEntityAttributesEvent(FabricDefaultAttributeRegistry::register));
        EventManager.invoke(new RegisterVillagerTradeEvent(
                (profession, level, trade) -> TradeOfferHelper.registerVillagerOffers(profession, level, (trades) -> trades.add(trade))));
        EventManager.invoke(new RegisterWanderingTraderTradeEvent(
                (rare, trade) -> TradeOfferHelper.registerWanderingTraderOffers(rare ? 2 : 1, (trades) -> trades.add(trade))));
    }
}
