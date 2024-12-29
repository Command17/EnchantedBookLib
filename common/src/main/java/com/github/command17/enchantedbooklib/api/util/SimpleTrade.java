package com.github.command17.enchantedbooklib.api.util;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record SimpleTrade(ItemCost price, @Nullable ItemCost secondaryPrice,
                          ItemStack stack, int maxTrades, int xp,
                          float priceMultiplier) implements VillagerTrades.ItemListing {
    @Override
    public MerchantOffer getOffer(Entity trader, RandomSource random) {
        return new MerchantOffer(this.price, Optional.ofNullable(this.secondaryPrice), this.stack, this.maxTrades, this.xp, this.priceMultiplier);
    }
}
