package com.github.command17.enchantedbooklib.api.client.events;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public abstract class ClientTickEvent extends Event {
    private final Minecraft client;

    public ClientTickEvent(Minecraft client) {
        this.client = client;
    }

    public Minecraft getClient() {
        return client;
    }

    public static class Pre extends ClientTickEvent {
        public Pre(Minecraft client) {
            super(client);
        }
    }

    public static class Post extends ClientTickEvent {
        public Post(Minecraft client) {
            super(client);
        }
    }
}
