package com.github.command17.enchantedbooklib.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.ClientSetupEvent;
import com.github.command17.enchantedbooklib.api.client.entrypoint.fabric.ClientEnchantedModInitializer;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ClientLifecycleEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ClientTickEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ModifyItemTooltipEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.level.fabric.ClientLevelTickEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterColorProviderEvent;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterParticleProviderEvent;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterRendererEvent;
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

        invokePostRegisterEvents();
        EventManager.invoke(new ClientSetupEvent(Minecraft.getInstance()));
    }

    private static void invokePostRegisterEvents() {
        EventManager.invoke(new RegisterParticleProviderEvent());
        EventManager.invoke(new RegisterRendererEvent.Layer());
        EventManager.invoke(new RegisterRendererEvent.Renderer());
        EventManager.invoke(new RegisterColorProviderEvent.BlockProvider());
        EventManager.invoke(new RegisterColorProviderEvent.ItemProvider());
    }
}
