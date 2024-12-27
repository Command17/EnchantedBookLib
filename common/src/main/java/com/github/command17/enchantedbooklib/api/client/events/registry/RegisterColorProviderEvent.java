package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.client.registry.ColorProviderRegistry;
import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterColorProviderEvent extends Event {
    @SafeVarargs
    public final void register(BlockColor color, Supplier<Block>... blocks) {
        ColorProviderRegistry.register(color, blocks);
    }

    @SafeVarargs
    public final void register(ItemColor color, Supplier<Item>... items) {
        ColorProviderRegistry.register(color, items);
    }

    public void register(BlockColor color, Block... blocks) {
        for (Block block: blocks) {
            ColorProviderRegistry.register(color, () -> block);
        }
    }

    public void register(ItemColor color, Item... items) {
        for (Item item: items) {
            ColorProviderRegistry.register(color, () -> item);
        }
    }
}
