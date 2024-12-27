package com.github.command17.enchantedbooklib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class ColorProviderRegistry {
    private ColorProviderRegistry() {}

    @SafeVarargs
    @ExpectPlatform
    public static void register(BlockColor color, Supplier<Block>... blocks) {
        throw new AssertionError();
    }

    @SafeVarargs
    @ExpectPlatform
    public static void register(ItemColor color, Supplier<Item>... items) {
        throw new AssertionError();
    }
}
