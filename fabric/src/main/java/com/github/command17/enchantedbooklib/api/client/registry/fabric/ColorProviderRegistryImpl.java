package com.github.command17.enchantedbooklib.api.client.registry.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class ColorProviderRegistryImpl {
    private ColorProviderRegistryImpl() {}

    @SafeVarargs
    public static void register(BlockColor color, Supplier<Block>... blocks) {
        for (Supplier<Block> block: blocks) {
            ColorProviderRegistry.BLOCK.register(color, block.get());
        }
    }

    @SafeVarargs
    public static void register(ItemColor color, Supplier<Item>... items) {
        for (Supplier<Item> item: items) {
            ColorProviderRegistry.ITEM.register(color, item.get());
        }
    }
}
