package com.github.command17.testmod.item;

import com.github.command17.enchantedbooklib.api.registry.IRegistrySupplier;
import com.github.command17.enchantedbooklib.api.registry.RegistryHelper;
import com.github.command17.testmod.TestMod;
import com.github.command17.testmod.item.tab.ModCreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    private static final RegistryHelper.ItemHelper REGISTRY = RegistryHelper.createItemHelper(TestMod.MOD_ID, ModCreativeModeTabs.MAIN.getKey());

    public static final IRegistrySupplier<Item> MAGIC_STICK = REGISTRY.registerItem("magic_stick",
            MagicStickItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));

    public static void register() {
        REGISTRY.register();
    }
}
