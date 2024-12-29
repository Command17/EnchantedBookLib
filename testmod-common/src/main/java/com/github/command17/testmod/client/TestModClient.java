package com.github.command17.testmod.client;

import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterRendererEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.testmod.TestMod;
import com.github.command17.testmod.client.event.ModClientEvents;
import com.github.command17.testmod.client.renderer.StrongTntRenderer;
import com.github.command17.testmod.entity.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class TestModClient {
    public static void init() {
        TestMod.LOGGER.info("Initializing Client...");

        EventManager.registerListeners(TestModClient.class);
        ModClientEvents.register();

        TestMod.LOGGER.info("Initialized Client.");
    }

    @EventListener
    private static void registerRenderers(RegisterRendererEvent event) {
        event.registerEntityRenderer(ModEntities.STRONG_TNT, StrongTntRenderer::new);
    }
}
