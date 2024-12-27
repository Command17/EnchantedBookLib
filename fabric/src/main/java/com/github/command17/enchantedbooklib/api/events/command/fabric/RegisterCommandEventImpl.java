package com.github.command17.enchantedbooklib.api.events.command.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.command.RegisterCommandEvent;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.jetbrains.annotations.ApiStatus;

public final class RegisterCommandEventImpl {
    private RegisterCommandEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registry ,selection) -> EventManager.invoke(new RegisterCommandEvent(dispatcher, registry, selection)));
    }
}
