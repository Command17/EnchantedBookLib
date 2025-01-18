package com.github.command17.enchantedbooklib.api.events.entity;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public class RightClickItemEvent extends Event implements ICancelableEvent {
    private final Player player;
    private final InteractionHand hand;

    private InteractionResult result;

    public RightClickItemEvent(Player player, InteractionHand hand) {
        this.player = player;
        this.hand = hand;
        this.result = InteractionResult.PASS;
    }

    public void setResult(InteractionResult result) {
        this.result = result;
    }

    public InteractionResult getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public InteractionHand getHand() {
        return hand;
    }
}
