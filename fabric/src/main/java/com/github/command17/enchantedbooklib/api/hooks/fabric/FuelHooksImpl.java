package com.github.command17.enchantedbooklib.api.hooks.fabric;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.FuelValues;

public final class FuelHooksImpl {
    private FuelHooksImpl() {}

    public static int getBurnTime(ItemStack stack, FuelValues fuelValues) {
        return fuelValues.burnDuration(stack);
    }
}
