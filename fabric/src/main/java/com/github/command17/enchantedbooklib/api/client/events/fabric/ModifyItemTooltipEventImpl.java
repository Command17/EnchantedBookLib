package com.github.command17.enchantedbooklib.api.client.events.fabric;

import com.github.command17.enchantedbooklib.api.client.events.ModifyItemTooltipEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import org.jetbrains.annotations.ApiStatus;

@Environment(EnvType.CLIENT)
public final class ModifyItemTooltipEventImpl {
    private ModifyItemTooltipEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        ItemTooltipCallback.EVENT.register((stack, context, flag, tooltips) -> EventManager.invoke(new ModifyItemTooltipEvent(stack, context, tooltips, flag)));
    }
}
