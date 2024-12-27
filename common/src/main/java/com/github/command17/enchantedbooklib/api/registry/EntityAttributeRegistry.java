package com.github.command17.enchantedbooklib.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public final class EntityAttributeRegistry {
    private EntityAttributeRegistry() {}

    @ExpectPlatform
    public static void registerEntityAttributes(Supplier<EntityType<? extends LivingEntity>> entityType, AttributeSupplier attributes) {
        throw new AssertionError();
    }
}
