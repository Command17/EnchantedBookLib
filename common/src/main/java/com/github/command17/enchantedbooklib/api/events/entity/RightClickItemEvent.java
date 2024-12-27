package com.github.command17.enchantedbooklib.api.events.entity;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class RightClickItemEvent extends Event implements ICancelableEvent {
    private final Player player;
    private final InteractionHand hand;

    private InteractionResultHolder<ItemStack> result;

    public RightClickItemEvent(Player player, InteractionHand hand) {
        this.player = player;
        this.hand = hand;
        this.result = InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    public void setResult(InteractionResultHolder<ItemStack> result) {
        this.result = result;
    }

    public InteractionResultHolder<ItemStack> getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public InteractionHand getHand() {
        return hand;
    }
}
