package com.github.command17.enchantedbooklib.api.hooks.neoforge;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class HoeItemHooksImpl {
    private HoeItemHooksImpl() {}

    public static void addTillable(Block base, Predicate<UseOnContext> usePredicate, Consumer<UseOnContext> useConsumer, Function<UseOnContext, BlockState> resultGetter) {
        NeoForge.EVENT_BUS.<BlockEvent.BlockToolModificationEvent>addListener((event) -> {
            UseOnContext context = event.getContext();

            if (ItemAbilities.HOE_TILL == event.getItemAbility()
                    && context.getItemInHand().canPerformAction(ItemAbilities.HOE_TILL)
                    && event.getState().is(base)
                    && usePredicate.test(context)) {
                if (!event.isSimulated()) {
                    useConsumer.accept(context);
                }

                event.setFinalState(resultGetter.apply(context));
            }
        });
    }
}
