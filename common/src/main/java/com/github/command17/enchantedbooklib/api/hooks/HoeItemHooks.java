package com.github.command17.enchantedbooklib.api.hooks;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class HoeItemHooks {
    private HoeItemHooks() {}

    @ExpectPlatform
    public static void addTillable(Block base, Predicate<UseOnContext> usePredicate, Consumer<UseOnContext> useConsumer, Function<UseOnContext, BlockState> resultGetter) {
        throw new AssertionError();
    }
}
