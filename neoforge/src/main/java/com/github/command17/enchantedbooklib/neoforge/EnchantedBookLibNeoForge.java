package com.github.command17.enchantedbooklib.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.util.EnvSide;
import com.github.command17.enchantedbooklib.api.util.EnvSideExecutor;
import com.github.command17.enchantedbooklib.client.EnchantedBookLibClient;
import net.neoforged.fml.common.Mod;

@Mod(EnchantedBookLib.MOD_ID)
public final class EnchantedBookLibNeoForge {
    public EnchantedBookLibNeoForge() {
        EnchantedBookLib.init();
        EnvSideExecutor.runOn(EnvSide.CLIENT, EnchantedBookLibClient::init);
    }
}
