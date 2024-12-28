package com.github.command17.testmod.fabric.client;

import com.github.command17.enchantedbooklib.api.client.entrypoint.fabric.ClientEnchantedModInitializer;
import com.github.command17.testmod.client.TestModClient;

public final class TestModFabricClient implements ClientEnchantedModInitializer {
    @Override
    public void onInitializeClient() {
        TestModClient.init();
    }
}
