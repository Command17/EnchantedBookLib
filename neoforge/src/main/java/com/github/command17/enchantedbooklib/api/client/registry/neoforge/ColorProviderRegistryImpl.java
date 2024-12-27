package com.github.command17.enchantedbooklib.api.client.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.google.common.collect.ArrayListMultimap;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ColorProviderRegistryImpl {
    private ColorProviderRegistryImpl() {}

    private static final ArrayListMultimap<BlockColor, Supplier<Block>> BLOCK_COLOR_MAP = ArrayListMultimap.create();
    private static final ArrayListMultimap<ItemColor, Supplier<Item>> ITEM_COLOR_MAP = ArrayListMultimap.create();

    @SafeVarargs
    public static void register(BlockColor color, Supplier<Block>... blocks) {
        for (Supplier<Block> block: blocks) {
            BLOCK_COLOR_MAP.put(color, block);
        }
    }

    @SafeVarargs
    public static void register(ItemColor color, Supplier<Item>... items) {
        for (Supplier<Item> item: items) {
            ITEM_COLOR_MAP.put(color, item);
        }
    }

    @SubscribeEvent
    private static void event(RegisterColorHandlersEvent.Block event) {
        BLOCK_COLOR_MAP.forEach((color, block) -> event.register(color, block.get()));
    }

    @SubscribeEvent
    private static void event(RegisterColorHandlersEvent.Item event) {
        ITEM_COLOR_MAP.forEach((color, item) -> event.register(color, item.get()));
    }
}
