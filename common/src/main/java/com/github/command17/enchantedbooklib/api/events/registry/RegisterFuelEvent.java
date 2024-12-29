package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.world.level.ItemLike;

import java.util.function.BiConsumer;

public class RegisterFuelEvent extends Event {
    private final BiConsumer<Integer, ItemLike> registrar;

    public RegisterFuelEvent(BiConsumer<Integer, ItemLike> registrar) {
        this.registrar = registrar;
    }

    public void register(int time, ItemLike... items) {
        for (ItemLike item: items) {
            this.registrar.accept(time, item);
        }
    }
}
