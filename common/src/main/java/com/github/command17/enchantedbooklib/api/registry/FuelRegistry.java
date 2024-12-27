package com.github.command17.enchantedbooklib.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public final class FuelRegistry {
    private FuelRegistry() {}

    @ExpectPlatform
    public static void registerFuel(int time, ItemLike... items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getBurningTime(ItemStack stack) {
        throw new AssertionError();
    }
}
