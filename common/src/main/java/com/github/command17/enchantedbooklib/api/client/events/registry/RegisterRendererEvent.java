package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.client.registry.RendererRegistry;
import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterRendererEvent extends Event {
    public<T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> entityType, EntityRendererProvider<T> renderer) {
        RendererRegistry.registerEntityRenderer(entityType, renderer);
    }

    public void registerEntityModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> layerDefinition) {
        RendererRegistry.registerEntityModelLayer(location, layerDefinition);
    }

    public<T extends BlockEntity> void registerBlockEntityRenderer(Supplier<? extends BlockEntityType<? extends T>> blockEntityType, BlockEntityRendererProvider<T> renderer) {
        RendererRegistry.registerBlockEntityRenderer(blockEntityType, renderer);
    }

    public<T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> renderer) {
        RendererRegistry.registerEntityRenderer(() -> entityType, renderer);
    }

    public void registerEntityModelLayer(ModelLayerLocation location, LayerDefinition layerDefinition) {
        RendererRegistry.registerEntityModelLayer(location, () -> layerDefinition);
    }

    public<T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> renderer) {
        RendererRegistry.registerBlockEntityRenderer(() -> blockEntityType, renderer);
    }
}
