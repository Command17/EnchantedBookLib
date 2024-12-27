package com.github.command17.enchantedbooklib.api.events.command.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.command.RegisterCommandEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class RegisterCommandEventImpl {
    private RegisterCommandEventImpl() {}

    @SubscribeEvent
    private static void event(RegisterCommandsEvent event) {
        EventManager.invoke(new RegisterCommandEvent(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
    }
}
