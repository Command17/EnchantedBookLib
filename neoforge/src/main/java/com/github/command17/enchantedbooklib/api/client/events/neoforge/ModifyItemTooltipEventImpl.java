package com.github.command17.enchantedbooklib.api.client.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.ModifyItemTooltipEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ModifyItemTooltipEventImpl {
    private ModifyItemTooltipEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ItemTooltipEvent event) {
        EventManager.invoke(new ModifyItemTooltipEvent(event.getItemStack(), event.getContext(), event.getToolTip(), event.getFlags()));
    }
}
