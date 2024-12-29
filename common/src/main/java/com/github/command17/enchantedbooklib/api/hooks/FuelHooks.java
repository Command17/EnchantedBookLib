package com.github.command17.enchantedbooklib.api.hooks;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.ItemStack;

public final class FuelHooks {
    private FuelHooks() {}

    @ExpectPlatform
    public static int getBurnTime(ItemStack stack) {
        throw new AssertionError();
    }
}
