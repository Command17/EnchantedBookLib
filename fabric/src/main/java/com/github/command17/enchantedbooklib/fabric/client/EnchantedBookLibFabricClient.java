package com.github.command17.enchantedbooklib.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.ClientSetupEvent;
import com.github.command17.enchantedbooklib.api.client.entrypoint.fabric.ClientEnchantedModInitializer;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ClientLifecycleEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ClientTickEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ModifyItemTooltipEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.level.fabric.ClientLevelTickEventImpl;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.client.EnchantedBookLibClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public final class EnchantedBookLibFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Bind events
        ClientLifecycleEventImpl.register();
        ClientTickEventImpl.register();
        ModifyItemTooltipEventImpl.register();
        ClientLevelTickEventImpl.register();

        // Init main
        EnchantedBookLibClient.init();

        // Initialize custom entrypoint
        FabricLoader.getInstance().getEntrypoints("enchanted-client", ClientEnchantedModInitializer.class)
                .forEach(ClientEnchantedModInitializer::onInitializeClient);

        EventManager.invoke(new ClientSetupEvent(Minecraft.getInstance()));
    }
}
