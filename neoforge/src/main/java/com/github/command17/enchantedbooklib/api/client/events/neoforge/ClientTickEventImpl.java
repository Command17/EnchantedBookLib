package com.github.command17.enchantedbooklib.api.client.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ClientTickEventImpl {
    private ClientTickEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ClientTickEvent.Pre event) {
        EventManager.invoke(new com.github.command17.enchantedbooklib.api.client.events.ClientTickEvent.Pre(Minecraft.getInstance()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ClientTickEvent.Post event) {
        EventManager.invoke(new com.github.command17.enchantedbooklib.api.client.events.ClientTickEvent.Post(Minecraft.getInstance()));
    }
}
