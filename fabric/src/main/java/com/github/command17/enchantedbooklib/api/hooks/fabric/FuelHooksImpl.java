package com.github.command17.enchantedbooklib.api.hooks.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.ItemStack;

public final class FuelHooksImpl {
    private FuelHooksImpl() {}

    public static int getBurnTime(ItemStack stack) {
        return FuelRegistry.INSTANCE.get(stack.getItem());
    }
}
