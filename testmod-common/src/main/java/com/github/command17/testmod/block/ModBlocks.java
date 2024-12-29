package com.github.command17.testmod.block;

import com.github.command17.enchantedbooklib.api.registry.IRegistrySupplier;
import com.github.command17.enchantedbooklib.api.registry.RegistryHelper;
import com.github.command17.testmod.TestMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class ModBlocks {
    private static final RegistryHelper.BlockHelper REGISTRY = RegistryHelper.createBlockHelper(TestMod.MOD_ID);

    public static IRegistrySupplier<Block> STRONG_TNT = REGISTRY.registerBlock("strong_tnt", StrongTntBlock::new, () -> Blocks.TNT);

    public static void register() {
        REGISTRY.register();
    }
}
