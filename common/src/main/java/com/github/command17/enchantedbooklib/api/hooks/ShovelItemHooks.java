package com.github.command17.enchantedbooklib.api.hooks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public final class ShovelItemHooks {
    private ShovelItemHooks() {}

    public static void addFlattenable(Block base, Block result) {
        addFlattenable(base, result.defaultBlockState());
    }

    public static void addFlattenable(Block base, BlockState result) {
        if (ShovelItem.FLATTENABLES instanceof ImmutableMap<Block, BlockState>) {
            ShovelItem.FLATTENABLES = new HashMap<>(ShovelItem.FLATTENABLES);
        }

        ShovelItem.FLATTENABLES.put(base, result);
    }
}
