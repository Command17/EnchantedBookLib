package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.level.BlockEvent;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGameModeMixin {
    @Shadow protected ServerLevel level;

    @Shadow @Final protected ServerPlayer player;

    @Inject(
            method = "destroyBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"
            ),
            cancellable = true
    )
    private void enchantedbooklib$destroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir, @Local BlockState state) {
        BlockEvent.Break blockBreakEvent = new BlockEvent.Break(this.level, pos, state, this.player);
        EventManager.invoke(blockBreakEvent);

        if (blockBreakEvent.isCanceled()) {
            cir.setReturnValue(false);
        }
    }
}
