package com.github.command17.enchantedbooklib.api.events.entity.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.RightClickItemEvent;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import org.jetbrains.annotations.ApiStatus;

public final class RightClickItemEventImpl {
    private RightClickItemEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        UseItemCallback.EVENT.register((player, level, hand) -> {
            RightClickItemEvent rightClickItemEvent = new RightClickItemEvent(player, hand);
            EventManager.invoke(rightClickItemEvent);
            return rightClickItemEvent.getResult();
        });
    }
}
