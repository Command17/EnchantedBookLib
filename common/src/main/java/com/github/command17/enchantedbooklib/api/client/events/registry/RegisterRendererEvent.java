package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterRendererEvent extends Event {
    private final BiConsumer<EntityType<?>, EntityRendererProvider<?>> entityRendererRegistrar;
    private final BiConsumer<BlockEntityType<?>, BlockEntityRendererProvider<?>> blockEntityRendererRegistrar;

    public RegisterRendererEvent(BiConsumer<EntityType<?>, EntityRendererProvider<?>> entityRendererRegistrar, BiConsumer<BlockEntityType<?>, BlockEntityRendererProvider<?>> blockEntityRendererRegistrar) {
        this.entityRendererRegistrar = entityRendererRegistrar;
        this.blockEntityRendererRegistrar = blockEntityRendererRegistrar;
    }

    public<T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> entityType, EntityRendererProvider<T> renderer) {
        this.entityRendererRegistrar.accept(entityType.get(), renderer);
    }

    public<T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> renderer) {
        this.entityRendererRegistrar.accept(entityType, renderer);
    }

    public<T extends BlockEntity> void registerBlockEntityRenderer(Supplier<? extends BlockEntityType<? extends T>> blockEntityType, BlockEntityRendererProvider<T> renderer) {
        this.blockEntityRendererRegistrar.accept(blockEntityType.get(), renderer);
    }

    public<T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> renderer) {
        this.blockEntityRendererRegistrar.accept(blockEntityType, renderer);
    }
}
