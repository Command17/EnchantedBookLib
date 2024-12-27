package com.github.command17.enchantedbooklib.api.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class FuelRegistryImpl {
    private FuelRegistryImpl() {}

    private static final Object2IntOpenHashMap<ItemLike> FUEL_MAP = new Object2IntOpenHashMap<>();

    public static void registerFuel(int time, ItemLike... items) {
        for (ItemLike item: items) {
            FUEL_MAP.put(item, time);
        }
    }

    public static int getBurningTime(ItemStack stack) {
        return stack.getBurnTime(null);
    }

    @SubscribeEvent
    private static void event(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();

        if (FUEL_MAP.containsKey(stack.getItem())) {
            event.setBurnTime(FUEL_MAP.getInt(stack.getItem()));
        }
    }
}
