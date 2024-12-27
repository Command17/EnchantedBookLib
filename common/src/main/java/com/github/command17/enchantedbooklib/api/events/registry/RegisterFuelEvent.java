package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.registry.FuelRegistry;
import net.minecraft.world.level.ItemLike;

public class RegisterFuelEvent extends Event {
    public void register(int time, ItemLike... items) {
        FuelRegistry.registerFuel(time, items);
    }
}
