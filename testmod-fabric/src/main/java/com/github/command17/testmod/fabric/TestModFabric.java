package com.github.command17.testmod.fabric;

import com.github.command17.enchantedbooklib.api.entrypoint.fabric.EnchantedModInitializer;
import com.github.command17.testmod.TestMod;

public final class TestModFabric implements EnchantedModInitializer {
    @Override
    public void onInitialize() {
        TestMod.init();
    }
}
