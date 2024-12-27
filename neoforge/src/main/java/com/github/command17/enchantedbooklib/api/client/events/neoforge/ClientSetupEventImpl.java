package com.github.command17.enchantedbooklib.api.client.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.ClientSetupEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientSetupEventImpl {
    private ClientSetupEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(FMLClientSetupEvent event) {
        EventManager.invoke(new ClientSetupEvent(Minecraft.getInstance()));
    }
}
