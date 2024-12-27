package com.github.command17.enchantedbooklib.api.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class EntityAttributeRegistryImpl {
    private EntityAttributeRegistryImpl() {}

    private static final ConcurrentHashMap<Supplier<EntityType<? extends LivingEntity>>, AttributeSupplier> ATTRIBUTE_MAP = new ConcurrentHashMap<>();

    public static void registerEntityAttributes(Supplier<EntityType<? extends LivingEntity>> entityType, AttributeSupplier attributes) {
        ATTRIBUTE_MAP.put(entityType, attributes);
    }

    @SubscribeEvent
    private static void event(EntityAttributeCreationEvent event) {
        ATTRIBUTE_MAP.forEach((entityType, attributes) -> event.put(entityType.get(), attributes));
    }
}
