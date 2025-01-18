package com.github.command17.testmod.item;

import com.github.command17.testmod.TestMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MagicStickItem extends Item {
    public MagicStickItem(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        player.displayClientMessage(Component.literal("Is Client: " + level.isClientSide), false);

        if (player.isShiftKeyDown()) {
            if (TestMod.CONFIG.syncedBoolValue.get()) {
                if (level.isClientSide) {
                    player.displayClientMessage(Component.literal("syncedBoolValue is enabled on the server!"), false);
                } else {
                    player.displayClientMessage(Component.literal("syncedBoolValue is enabled!"), false);
                }
            }
        } else {
            if (TestMod.CONFIG.boolValue.get()) {
                player.displayClientMessage(Component.literal("boolValue is enabled!"), false);
            }

            player.displayClientMessage(Component.literal(Arrays.toString(TestMod.CONFIG.stringArray.get())), false);
        }

        return InteractionResult.SUCCESS.heldItemTransformedTo(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
