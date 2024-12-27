package com.github.command17.enchantedbooklib.api.client.registry.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class RendererRegistryImpl {
    private RendererRegistryImpl() {}

    public static<T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> entityType, EntityRendererProvider<T> renderer) {
        EntityRendererRegistry.register(entityType.get(), renderer);
    }

    public static void registerEntityModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> layerDefinition) {
        EntityModelLayerRegistry.registerModelLayer(location, layerDefinition::get);
    }

    public static<T extends BlockEntity> void registerBlockEntityRenderer(Supplier<? extends BlockEntityType<? extends T>> blockEntityType, BlockEntityRendererProvider<T> renderer) {
        BlockEntityRenderers.register(blockEntityType.get(), renderer);
    }
}
