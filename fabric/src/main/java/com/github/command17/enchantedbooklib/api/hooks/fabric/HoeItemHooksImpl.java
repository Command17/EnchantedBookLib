package com.github.command17.enchantedbooklib.api.hooks.fabric;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class HoeItemHooksImpl {
    private HoeItemHooksImpl() {}

    public static void addTillable(Block base, Predicate<UseOnContext> usePredicate, Consumer<UseOnContext> useConsumer, Function<UseOnContext, BlockState> resultGetter) {
        if (HoeItem.TILLABLES instanceof ImmutableMap<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>>) {
            HoeItem.TILLABLES = new HashMap<>(HoeItem.TILLABLES);
        }

        HoeItem.TILLABLES.put(base, new Pair<>(usePredicate, (context) -> {
            useConsumer.accept(context);
            BlockState state = resultGetter.apply(context);
            context.getLevel().setBlock(context.getClickedPos(), state, 11);
        }));
    }
}
