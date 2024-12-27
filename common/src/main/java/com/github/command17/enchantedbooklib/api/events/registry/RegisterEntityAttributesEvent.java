package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.registry.EntityAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public class RegisterEntityAttributesEvent extends Event {
    public void registerEntityAttributes(Supplier<EntityType<? extends LivingEntity>> entityType, AttributeSupplier attributes) {
        EntityAttributeRegistry.registerEntityAttributes(entityType, attributes);
    }

    public void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, AttributeSupplier attributes) {
        EntityAttributeRegistry.registerEntityAttributes(() -> entityType, attributes);
    }
}
