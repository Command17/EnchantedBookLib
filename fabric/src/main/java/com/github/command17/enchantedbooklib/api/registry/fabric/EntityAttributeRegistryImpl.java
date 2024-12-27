package com.github.command17.enchantedbooklib.api.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public final class EntityAttributeRegistryImpl {
    private EntityAttributeRegistryImpl() {}

    public static void registerEntityAttributes(Supplier<EntityType<? extends LivingEntity>> entityType, AttributeSupplier attributes) {
        FabricDefaultAttributeRegistry.register(entityType.get(), attributes);
    }
}
