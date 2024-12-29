package com.github.command17.enchantedbooklib.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.ClientSetupEvent;
import com.github.command17.enchantedbooklib.api.client.entrypoint.fabric.ClientEnchantedModInitializer;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ClientLifecycleEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ClientTickEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.fabric.ModifyItemTooltipEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.level.fabric.ClientLevelTickEventImpl;
import com.github.command17.enchantedbooklib.api.client.events.registry.*;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.client.EnchantedBookLibClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;

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

    @SuppressWarnings("unchecked")
    private static void invokePostRegisterEvents() {
        EventManager.invoke(new RegisterParticleProviderEvent(
                (particleType, provider) -> ParticleFactoryRegistry.getInstance().register((ParticleType<ParticleOptions>) particleType, (ParticleFactoryRegistry.PendingParticleFactory<ParticleOptions>) provider)));
        EventManager.invoke(new RegisterEntityLayerEvent(
                (id, layer) -> EntityModelLayerRegistry.registerModelLayer(id, layer::get)));
        EventManager.invoke(new RegisterRendererEvent(
                (entityType, renderer) -> EntityRendererRegistry.register(entityType, (EntityRendererProvider<Entity>) renderer),
                (blockEntityType, renderer) -> BlockEntityRenderers.register(blockEntityType, (BlockEntityRendererProvider<BlockEntity>) renderer)));
        EventManager.invoke(new RegisterBlockColorProviderEvent(ColorProviderRegistry.BLOCK::register));
        EventManager.invoke(new RegisterItemColorProviderEvent(ColorProviderRegistry.ITEM::register));
    }
}
