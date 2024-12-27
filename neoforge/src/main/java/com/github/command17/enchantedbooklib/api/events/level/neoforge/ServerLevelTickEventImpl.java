package com.github.command17.enchantedbooklib.api.events.level.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.level.ServerLevelTickEvent;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class ServerLevelTickEventImpl {
    private ServerLevelTickEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LevelTickEvent.Pre event) {
        if (event.getLevel() instanceof ServerLevel level) {
            EventManager.invoke(new ServerLevelTickEvent.Pre(level));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) {
            EventManager.invoke(new ServerLevelTickEvent.Post(level));
        }
    }
}
