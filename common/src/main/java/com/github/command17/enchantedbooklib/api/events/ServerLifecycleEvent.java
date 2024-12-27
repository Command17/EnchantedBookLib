package com.github.command17.enchantedbooklib.api.events;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.server.MinecraftServer;

public abstract class ServerLifecycleEvent extends Event {
    private final MinecraftServer server;

    public ServerLifecycleEvent(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer getServer() {
        return server;
    }

    /**
     * Event Details: <b>Server only event<b/>
     */
    public static class Starting extends ServerLifecycleEvent {
        public Starting(MinecraftServer server) {
            super(server);
        }
    }

    /**
     * Event Details: <b>Server only event<b/>
     */
    public static class Stopping extends ServerLifecycleEvent {
        public Stopping(MinecraftServer server) {
            super(server);
        }
    }

    /**
     * Event Details: <b>Server only event<b/>
     */
    public static class Started extends ServerLifecycleEvent {
        public Started(MinecraftServer server) {
            super(server);
        }
    }

    /**
     * Event Details: <b>Server only event<b/>
     */
    public static class Stopped extends ServerLifecycleEvent {
        public Stopped(MinecraftServer server) {
            super(server);
        }
    }
}
