package com.github.command17.enchantedbooklib.api.client.events.input;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public abstract class RawMouseClickEvent extends Event {
    private final Minecraft client;
    private final int button;
    private final int action;
    private final int modifiers;

    public RawMouseClickEvent(Minecraft client, int button, int action, int modifiers) {
        this.client = client;
        this.button = button;
        this.action = action;
        this.modifiers = modifiers;
    }

    public Minecraft getClient() {
        return client;
    }

    public int getButton() {
        return button;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }

    public static class Pre extends RawMouseClickEvent implements ICancelableEvent {
        public Pre(Minecraft client, int button, int action, int modifiers) {
            super(client, button, action, modifiers);
        }
    }

    public static class Post extends RawMouseClickEvent implements ICancelableEvent {
        public Post(Minecraft client, int button, int action, int modifiers) {
            super(client, button, action, modifiers);
        }
    }
}
