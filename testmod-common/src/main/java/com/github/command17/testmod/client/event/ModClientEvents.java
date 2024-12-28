package com.github.command17.testmod.client.event;

import com.github.command17.enchantedbooklib.api.client.events.ClientLifecycleEvent;
import com.github.command17.enchantedbooklib.api.client.events.input.RawKeyPressEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.testmod.TestMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public final class ModClientEvents {
    private ModClientEvents() {}

    @EventListener
    private static void onRawKeyPress(RawKeyPressEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS) {
            TestMod.LOGGER.info("Pressed key '{}'", event.getKeyCode());
        }
    }

    @EventListener
    private static void onClientStart(ClientLifecycleEvent.Started event) {
        TestMod.LOGGER.info("Client Started!");
    }

    @EventListener
    private static void onClientStop(ClientLifecycleEvent.Stopped event) {
        TestMod.LOGGER.info("Client Stopped!");
    }

    public static void register() {
        EventManager.registerListeners(ModClientEvents.class);
    }
}
