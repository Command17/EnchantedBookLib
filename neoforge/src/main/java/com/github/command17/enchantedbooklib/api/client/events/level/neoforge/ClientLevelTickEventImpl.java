package com.github.command17.enchantedbooklib.api.client.events.level.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.level.ClientLevelTickEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.multiplayer.ClientLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ClientLevelTickEventImpl {
    private ClientLevelTickEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LevelTickEvent.Pre event) {
        if (event.getLevel() instanceof ClientLevel level) {
            EventManager.invoke(new ClientLevelTickEvent.Pre(level));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ClientLevel level) {
            EventManager.invoke(new ClientLevelTickEvent.Post(level));
        }
    }
}
