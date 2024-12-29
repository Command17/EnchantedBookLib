package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class RegisterEntityAttributesEvent extends Event {
    private final BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> registrar;

    public RegisterEntityAttributesEvent(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> registrar) {
        this.registrar = registrar;
    }

    public void registerEntityAttributes(Supplier<EntityType<? extends LivingEntity>> entityType, AttributeSupplier attributes) {
        this.registrar.accept(entityType.get(), attributes);
    }

    public void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, AttributeSupplier attributes) {
        this.registrar.accept(entityType, attributes);
    }
}
