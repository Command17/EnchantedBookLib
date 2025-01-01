package com.github.command17.testmod.neoforge;

import com.github.command17.enchantedbooklib.api.util.EnvSide;
import com.github.command17.enchantedbooklib.api.util.EnvSideExecutor;
import com.github.command17.testmod.TestMod;
import com.github.command17.testmod.client.TestModClient;
import net.neoforged.fml.common.Mod;

@Mod(TestMod.MOD_ID)
public final class TestModNeoForge {
    public TestModNeoForge() {
        TestMod.init();
        EnvSideExecutor.runOn(EnvSide.CLIENT, TestModClient::init);
    }
}
