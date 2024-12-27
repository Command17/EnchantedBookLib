package com.github.command17.enchantedbooklib;

import com.github.command17.enchantedbooklib.api.config.LibConfigs;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.events.SetupEvent;
import com.github.command17.enchantedbooklib.api.events.entity.PlayerEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterEntityAttributesEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterFuelEvent;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterVillagerTradeEvent;
import com.github.command17.enchantedbooklib.api.hooks.MinecraftServerHooks;
import com.github.command17.enchantedbooklib.api.network.NetworkingHelper;
import com.github.command17.enchantedbooklib.core.SyncConfigPacket;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;

public final class EnchantedBookLib {
    public static final String MOD_ID = "enchantedbooklib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        LOGGER.info("Initializing...");

        // Bind events
        EventManager.registerListeners(EnchantedBookLib.class);
        EventManager.registerListeners(MinecraftServerHooks.class);

        NetworkingHelper.registerS2CPayloadType(SyncConfigPacket.TYPE, SyncConfigPacket.CODEC);

        LOGGER.info("Initialized.");
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @EventListener
    private static void invokePostRegistrationEvents(SetupEvent event) {
        EventManager.invoke(new RegisterFuelEvent());
        EventManager.invoke(new RegisterVillagerTradeEvent());
        EventManager.invoke(new RegisterEntityAttributesEvent());
    }

    @EventListener
    private static void onPlayerJoin(PlayerEvent.Join event) {
        if (NetworkingHelper.canSendToClient(SyncConfigPacket.TYPE, event.getPlayer())) {
            LibConfigs.getSyncableConfigs().forEach((config) -> NetworkingHelper.sendToClient(new SyncConfigPacket(config), event.getPlayer()));
        }
    }
}
