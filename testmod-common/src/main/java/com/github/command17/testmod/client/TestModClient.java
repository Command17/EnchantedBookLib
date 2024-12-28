package com.github.command17.testmod.client;

import com.github.command17.testmod.TestMod;
import com.github.command17.testmod.client.event.ModClientEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class TestModClient {
    public static void init() {
        TestMod.LOGGER.info("Initializing Client...");

        ModClientEvents.register();

        TestMod.LOGGER.info("Initialized Client.");
    }
}
