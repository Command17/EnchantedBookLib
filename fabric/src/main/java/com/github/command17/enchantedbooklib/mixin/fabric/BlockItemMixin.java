package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.level.BlockEvent;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(
            method = "place",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/BlockItem;placeBlock(Lnet/minecraft/world/item/context/BlockPlaceContext;Lnet/minecraft/world/level/block/state/BlockState;)Z"
            ),
            cancellable = true
    )
    private void enchantedbooklib$place(BlockPlaceContext _0, CallbackInfoReturnable<InteractionResult> cir, @Local(ordinal = 1) BlockPlaceContext context, @Local BlockState state) {
        if (context.getLevel().isClientSide) {
            return;
        }

        BlockEvent.Place blockPlaceEvent = new BlockEvent.Place(context.getLevel(), context.getClickedPos(), state, context.getPlayer());
        EventManager.invoke(blockPlaceEvent);

        if (blockPlaceEvent.isCanceled()) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
