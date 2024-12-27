package com.github.command17.enchantedbooklib.api.hooks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

public final class AxeItemHooks {
    private AxeItemHooks() {}

    public static void addStrippable(Block base, Block result) {
        if (AxeItem.STRIPPABLES instanceof ImmutableMap<Block, Block>) {
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
        }

        AxeItem.STRIPPABLES.put(base, result);
    }
}
