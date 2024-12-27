package com.github.command17.enchantedbooklib.api.events.level;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.server.level.ServerLevel;

public abstract class ServerLevelTickEvent extends Event {
    private final ServerLevel level;

    public ServerLevelTickEvent(ServerLevel level) {
        this.level = level;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public static class Pre extends ServerLevelTickEvent {
        public Pre(ServerLevel level) {
            super(level);
        }
    }

    public static class Post extends ServerLevelTickEvent {
        public Post(ServerLevel level) {
            super(level);
        }
    }
}
