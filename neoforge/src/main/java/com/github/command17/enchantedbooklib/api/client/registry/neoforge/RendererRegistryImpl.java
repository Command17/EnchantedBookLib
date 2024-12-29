package com.github.command17.enchantedbooklib.api.client.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterRendererEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RendererRegistryImpl {
    private RendererRegistryImpl() {}

    private static final ConcurrentHashMap<Supplier<? extends EntityType<?>>, EntityRendererProvider<?>> ENTITY_RENDERER_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Supplier<? extends BlockEntityType<?>>, BlockEntityRendererProvider<?>> BLOCK_ENTITY_RENDERER_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<ModelLayerLocation, Supplier<LayerDefinition>> ENTITY_LAYER_MAP = new ConcurrentHashMap<>();

    public static<T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> entityType, EntityRendererProvider<T> renderer) {
        ENTITY_RENDERER_MAP.put(entityType, renderer);
    }

    public static void registerEntityModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> layerDefinition) {
        ENTITY_LAYER_MAP.put(location, layerDefinition);
    }

    public static<T extends BlockEntity> void registerBlockEntityRenderer(Supplier<? extends BlockEntityType<? extends T>> blockEntityType, BlockEntityRendererProvider<T> renderer) {
        BLOCK_ENTITY_RENDERER_MAP.put(blockEntityType, renderer);
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    private static void event(EntityRenderersEvent.RegisterRenderers event) {
        EventManager.invoke(new RegisterRendererEvent.Renderer());

        ENTITY_RENDERER_MAP.forEach(
                (entityType, renderer) -> event.registerEntityRenderer(entityType.get(), (EntityRendererProvider<Entity>) renderer));
        BLOCK_ENTITY_RENDERER_MAP.forEach(
                (blockEntityType, renderer) -> event.registerBlockEntityRenderer(blockEntityType.get(), (BlockEntityRendererProvider<BlockEntity>) renderer));

        EnchantedBookLib.LOGGER.info("REGISTERED");
    }

    @SubscribeEvent
    private static void event(EntityRenderersEvent.RegisterLayerDefinitions event) {
        EventManager.invoke(new RegisterRendererEvent.Layer());

        ENTITY_LAYER_MAP.forEach(event::registerLayerDefinition);
    }
}
