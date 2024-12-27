package com.github.command17.enchantedbooklib.api.client.events.level;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;

@Environment(EnvType.CLIENT)
public abstract class ClientLevelTickEvent extends Event {
    private final ClientLevel level;

    public ClientLevelTickEvent(ClientLevel level) {
        this.level = level;
    }

    public ClientLevel getLevel() {
        return level;
    }

    public static class Pre extends ClientLevelTickEvent {
        public Pre(ClientLevel level) {
            super(level);
        }
    }

    public static class Post extends ClientLevelTickEvent {
        public Post(ClientLevel level) {
            super(level);
        }
    }
}
