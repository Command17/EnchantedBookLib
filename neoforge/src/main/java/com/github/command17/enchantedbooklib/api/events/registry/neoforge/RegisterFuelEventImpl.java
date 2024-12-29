package com.github.command17.enchantedbooklib.api.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterFuelEvent;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class RegisterFuelEventImpl {
    private static final Object2IntArrayMap<ItemLike> FUEL_MAP = new Object2IntArrayMap<>();

    private static boolean fired = false;

    private RegisterFuelEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(FurnaceFuelBurnTimeEvent event) {
        if (!fired) {
            EventManager.invoke(new RegisterFuelEvent((time, item) -> FUEL_MAP.put(item, (int) time)));
            fired = true;
        }

        Item item = event.getItemStack().getItem();

        if (FUEL_MAP.containsKey(item)) {
            event.setBurnTime(FUEL_MAP.getInt(item));
        }
    }
}
