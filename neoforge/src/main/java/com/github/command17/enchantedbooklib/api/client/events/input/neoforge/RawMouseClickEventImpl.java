package com.github.command17.enchantedbooklib.api.client.events.input.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.input.RawMouseClickEvent;
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
public final class RawMouseClickEventImpl {
    private RawMouseClickEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(InputEvent.MouseButton.Pre event) {
        RawMouseClickEvent.Pre rawMouseClickEvent = new RawMouseClickEvent.Pre(Minecraft.getInstance(), event.getButton(), event.getAction(), event.getModifiers());
        EventManager.invoke(rawMouseClickEvent);
        event.setCanceled(rawMouseClickEvent.isCanceled());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(InputEvent.MouseButton.Post event) {
        EventManager.invoke(new RawMouseClickEvent.Post(Minecraft.getInstance(), event.getButton(), event.getAction(), event.getModifiers()));
    }
}
