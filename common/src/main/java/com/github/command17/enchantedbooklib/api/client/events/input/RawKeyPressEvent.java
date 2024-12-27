package com.github.command17.enchantedbooklib.api.client.events.input;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class RawKeyPressEvent extends Event {
    private final Minecraft client;
    private final int keyCode;
    private final int scanCode;
    private final int action;
    private final int modifiers;

    public RawKeyPressEvent(Minecraft client, int keyCode, int scanCode, int action, int modifiers) {
        this.client = client;
        this.keyCode = keyCode;
        this.scanCode = scanCode;
        this.action = action;
        this.modifiers = modifiers;
    }

    public Minecraft getClient() {
        return client;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }
}
