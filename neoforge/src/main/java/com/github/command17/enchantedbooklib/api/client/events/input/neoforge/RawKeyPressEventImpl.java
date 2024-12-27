package com.github.command17.enchantedbooklib.api.client.events.input.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.input.RawKeyPressEvent;
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
public final class RawKeyPressEventImpl {
    private RawKeyPressEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(InputEvent.Key event) {
        EventManager.invoke(new RawKeyPressEvent(Minecraft.getInstance(), event.getKey(), event.getScanCode(), event.getAction(), event.getModifiers()));
    }
}
