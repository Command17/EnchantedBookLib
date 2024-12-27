package com.github.command17.enchantedbooklib.api.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public final class FuelRegistryImpl {
    private FuelRegistryImpl() {}

    public static void registerFuel(int time, ItemLike... items) {
        for (ItemLike item: items) {
            if (time > 0) {
                FuelRegistry.INSTANCE.add(item, time);
            } else {
                FuelRegistry.INSTANCE.remove(item);
            }
        }
    }

    public static int getBurningTime(ItemStack stack) {
        Integer time = FuelRegistry.INSTANCE.get(stack.getItem());
        return time != null ? time : 0;
    }
}
