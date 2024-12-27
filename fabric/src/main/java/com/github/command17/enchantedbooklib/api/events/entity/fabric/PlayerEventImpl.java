package com.github.command17.enchantedbooklib.api.events.entity.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.PlayerEvent;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.ApiStatus;

public final class PlayerEventImpl {
    private PlayerEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        AttackEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            PlayerEvent.AttackEntity playerAttackEntityEvent = new PlayerEvent.AttackEntity(player, level, entity, hand, hitResult);
            EventManager.invoke(playerAttackEntityEvent);

            if (playerAttackEntityEvent.isCanceled()) {
                return InteractionResult.FAIL;
            }

            return InteractionResult.PASS;
        });
    }
}
