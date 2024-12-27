package com.github.command17.testmod;

import com.github.command17.enchantedbooklib.api.config.LibConfigs;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterFuelEvent;
import com.github.command17.testmod.config.ModCommonConfig;
import com.github.command17.testmod.event.ModEvents;
import com.github.command17.testmod.item.ModItems;
import com.github.command17.testmod.item.tab.ModCreativeModeTabs;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class TestMod {
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModCommonConfig CONFIG = LibConfigs.registerConfig(resource("common_config"), new ModCommonConfig());

    public static void init() {
        LOGGER.info("Initializing...");

        EventManager.registerListeners(TestMod.class);

        ModEvents.register();
        ModItems.register();
        ModCreativeModeTabs.register();

        LOGGER.info("Initialized.");
    }

    @EventListener
    private static void registerFuelItems(RegisterFuelEvent event) {
        event.register(1000, ModItems.MAGIC_STICK.get());
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
