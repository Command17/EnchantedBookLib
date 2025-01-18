package com.github.command17.enchantedbooklib.api.hooks.neoforge;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.FuelValues;

public final class FuelHooksImpl {
    private FuelHooksImpl() {}

    public static int getBurnTime(ItemStack stack, FuelValues fuelValues) {
        return stack.getBurnTime(null, fuelValues);
    }
}
