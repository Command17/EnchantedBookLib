package com.github.command17.enchantedbooklib.api.client.events;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class ClientSetupEvent extends Event {
    private final Minecraft client;

    public ClientSetupEvent(Minecraft client) {
        this.client = client;
    }

    public Minecraft getClient() {
        return client;
    }
}
