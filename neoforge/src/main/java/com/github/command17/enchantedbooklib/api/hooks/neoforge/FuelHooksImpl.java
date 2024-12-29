package com.github.command17.enchantedbooklib.api.hooks.neoforge;

import net.minecraft.world.item.ItemStack;

public final class FuelHooksImpl {
    private FuelHooksImpl() {}

    public static int getBurnTime(ItemStack stack) {
        return stack.getBurnTime(null);
    }
}
