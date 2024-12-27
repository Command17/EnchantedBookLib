package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.PlayerEvent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void enchantedbooklib$preTick(CallbackInfo ci) {
        EventManager.invoke(new PlayerEvent.PreTick((Player) ((Object) this)));
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void enchantedbooklib$postTick(CallbackInfo ci) {
        EventManager.invoke(new PlayerEvent.PostTick((Player) ((Object) this)));
    }

    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("RETURN"), cancellable = true)
    private void enchantedbooklib$dropItem(ItemStack itemStack, boolean includeThrowerName, CallbackInfoReturnable<ItemEntity> cir) {
        PlayerEvent.DropItem playerDropItemEvent = new PlayerEvent.DropItem((Player) ((Object) this), cir.getReturnValue());
        EventManager.invoke(playerDropItemEvent);

        if (playerDropItemEvent.isCanceled()) {
            cir.setReturnValue(null);
        }
    }
}
