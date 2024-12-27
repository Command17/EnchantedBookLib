package com.github.command17.testmod.item;

import com.github.command17.testmod.TestMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        player.sendSystemMessage(Component.literal("Is Client: " + level.isClientSide));

        if (player.isShiftKeyDown()) {
            if (TestMod.CONFIG.syncedBoolValue.get()) {
                if (level.isClientSide) {
                    player.sendSystemMessage(Component.literal("syncedBoolValue is enabled on the server!"));
                } else {
                    player.sendSystemMessage(Component.literal("syncedBoolValue is enabled!"));
                }
            }
        } else {
            if (TestMod.CONFIG.boolValue.get()) {
                player.sendSystemMessage(Component.literal("boolValue is enabled!"));
            }

            player.sendSystemMessage(Component.literal(Arrays.toString(TestMod.CONFIG.stringArray.get())));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
