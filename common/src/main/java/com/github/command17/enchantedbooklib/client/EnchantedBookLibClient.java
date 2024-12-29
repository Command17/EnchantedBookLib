package com.github.command17.enchantedbooklib.client;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.entity.ClientPlayerEvent;
import com.github.command17.enchantedbooklib.api.config.LibConfigs;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.network.NetworkingHelper;
import com.github.command17.enchantedbooklib.core.SyncConfigPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class EnchantedBookLibClient {
    public static void init() {
        EnchantedBookLib.LOGGER.info("Initializing Client...");

        EventManager.registerListeners(EnchantedBookLibClient.class);
        NetworkingHelper.registerS2CHandler(SyncConfigPacket.TYPE, (packet, config) -> {});

        EnchantedBookLib.LOGGER.info("Initialized Client.");
    }

    @EventListener
    private static void onPlayerQuit(ClientPlayerEvent.Quit event) {
        EnchantedBookLib.LOGGER.info("Unloading configs from server...");
        LibConfigs.getSyncableConfigs().forEach(
                (config) -> config.getEntries().forEach((entry) -> entry.setSyncedValue(null))
        );
    }
}
