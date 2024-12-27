package com.github.command17.enchantedbooklib.api.events.entity.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.BlockInteractionEvent;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.ApiStatus;

public final class BlockInteractionEventImpl {
    private BlockInteractionEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockInteractionEvent.RightClick rightClickBlockEvent = new BlockInteractionEvent.RightClick(player, hand, hitResult.getBlockPos(), hitResult.getDirection());
            EventManager.invoke(rightClickBlockEvent);

            if (rightClickBlockEvent.isCanceled()) {
                return InteractionResult.FAIL;
            }

            return InteractionResult.PASS;
        });

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            BlockInteractionEvent.LeftClick leftClickBlockEvent = new BlockInteractionEvent.LeftClick(player, hand, pos, direction);
            EventManager.invoke(leftClickBlockEvent);

            if (leftClickBlockEvent.isCanceled()) {
                return InteractionResult.FAIL;
            }

            return InteractionResult.PASS;
        });
    }
}
