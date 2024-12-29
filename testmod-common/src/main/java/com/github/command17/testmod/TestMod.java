package com.github.command17.testmod;

import com.github.command17.enchantedbooklib.api.config.LibConfigs;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterFuelEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterVillagerTradeEvent;
import com.github.command17.enchantedbooklib.api.util.SimpleTrade;
import com.github.command17.testmod.block.ModBlocks;
import com.github.command17.testmod.config.ModCommonConfig;
import com.github.command17.testmod.entity.ModEntities;
import com.github.command17.testmod.event.ModEvents;
import com.github.command17.testmod.item.ModItems;
import com.github.command17.testmod.item.tab.ModCreativeModeTabs;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public final class TestMod {
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModCommonConfig CONFIG = LibConfigs.registerConfig(resource("common_config"), new ModCommonConfig());

    public static void init() {
        LOGGER.info("Initializing...");

        EventManager.registerListeners(TestMod.class);

        ModEvents.register();
        ModBlocks.register();
        ModItems.register();
        ModEntities.register();
        ModCreativeModeTabs.register();

        LOGGER.info("Initialized.");
    }

    @EventListener
    private static void registerFuelItems(RegisterFuelEvent event) {
        event.register(1000, ModItems.MAGIC_STICK.get());
        event.register(2000, ModItems.FUEL_STICK.get());
    }

    @EventListener
    private static void registerTrades(RegisterVillagerTradeEvent event) {
        event.register(VillagerProfession.CLERIC, 2, new SimpleTrade(new ItemCost(Items.EMERALD, 15), null, new ItemStack(ModItems.STRONG_TNT.get()), 3, 15, 0.1f));
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
