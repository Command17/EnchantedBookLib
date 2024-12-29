package com.github.command17.testmod.item.tab;

import com.github.command17.enchantedbooklib.api.registry.CreativeModeTabRegistry;
import com.github.command17.enchantedbooklib.api.registry.IRegistrySupplier;
import com.github.command17.enchantedbooklib.api.registry.RegistryHelper;
import com.github.command17.testmod.TestMod;
import com.github.command17.testmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeModeTabs {
    private static final RegistryHelper<CreativeModeTab> REGISTRY = RegistryHelper.create(TestMod.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static IRegistrySupplier<CreativeModeTab> MAIN = REGISTRY.register("main",
            () -> CreativeModeTabRegistry.createTab(Component.translatable("tab." + TestMod.MOD_ID + ".main"), () -> new ItemStack(ModItems.MAGIC_STICK.get())));

    public static void register() {
        REGISTRY.register();
    }
}
