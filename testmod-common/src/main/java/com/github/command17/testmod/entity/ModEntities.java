package com.github.command17.testmod.entity;

import com.github.command17.enchantedbooklib.api.registry.IRegistrySupplier;
import com.github.command17.enchantedbooklib.api.registry.RegistryHelper;
import com.github.command17.testmod.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {
    private static final RegistryHelper<EntityType<?>> REGISTRY = RegistryHelper.create(TestMod.MOD_ID, Registries.ENTITY_TYPE);

    public static IRegistrySupplier<EntityType<StrongTntEntity>> STRONG_TNT = REGISTRY.register("strong_tnt",
            () -> EntityType.Builder.<StrongTntEntity>of(StrongTntEntity::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98f, 0.98f)
                    .eyeHeight(0.15f)
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .build(TestMod.resource("strong_tnt").toString()));

    public static void register() {
        REGISTRY.register();
    }
}
