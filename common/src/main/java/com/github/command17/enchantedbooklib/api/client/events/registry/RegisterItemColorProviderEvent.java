package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterItemColorProviderEvent extends Event {
    private final BiConsumer<ItemColor, Item> registrar;

    public RegisterItemColorProviderEvent(BiConsumer<ItemColor, Item> registrar) {
        this.registrar = registrar;
    }

    @SafeVarargs
    public final void register(ItemColor color, Supplier<Item>... items) {
        for (Supplier<Item> item: items) {
            this.registrar.accept(color, item.get());
        }
    }

    public void register(ItemColor color, Item... items) {
        for (Item item: items) {
            this.registrar.accept(color, item);
        }
    }
}
