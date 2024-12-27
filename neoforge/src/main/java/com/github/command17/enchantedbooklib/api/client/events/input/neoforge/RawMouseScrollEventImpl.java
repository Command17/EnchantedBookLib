package com.github.command17.enchantedbooklib.api.client.events.input.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.input.RawMouseScrollEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class RawMouseScrollEventImpl {
    private RawMouseScrollEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(InputEvent.MouseScrollingEvent event) {
        RawMouseScrollEvent rawMouseScrollEvent = new RawMouseScrollEvent(Minecraft.getInstance(), event.getScrollDeltaX(), event.getScrollDeltaY());
        EventManager.invoke(rawMouseScrollEvent);
        event.setCanceled(rawMouseScrollEvent.isCanceled());
    }
}
