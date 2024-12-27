package com.github.command17.enchantedbooklib.api.client.events;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public abstract class ClientLifecycleEvent extends Event {
    private final Minecraft client;

    public ClientLifecycleEvent(Minecraft client) {
        this.client = client;
    }

    public Minecraft getClient() {
        return client;
    }

    public static class Started extends ClientLifecycleEvent {
        public Started(Minecraft client) {
            super(client);
        }
    }

    public static class Stopped extends ClientLifecycleEvent {
        public Stopped(Minecraft client) {
            super(client);
        }
    }
}
