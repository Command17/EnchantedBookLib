package com.github.command17.enchantedbooklib.api.client.events.input;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.client.Minecraft;

public class RawMouseScrollEvent extends Event implements ICancelableEvent {
    private final Minecraft client;
    private final double amountX;
    private final double amountY;

    public RawMouseScrollEvent(Minecraft client, double amountX, double amountY) {
        this.client = client;
        this.amountX = amountX;
        this.amountY = amountY;
    }

    public Minecraft getClient() {
        return client;
    }

    public double getAmountX() {
        return amountX;
    }

    public double getAmountY() {
        return amountY;
    }
}
