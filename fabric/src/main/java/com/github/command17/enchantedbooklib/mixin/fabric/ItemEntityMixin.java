package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.PlayerEvent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getItem();

    @Unique
    private ItemStack itemCache;

    @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"), cancellable = true)
    private void enchantedbooklib$beforePickUp(Player player, CallbackInfo ci) {
        this.itemCache = this.getItem().copy();
        PlayerEvent.BeforePickUpItem playerBeforePickUpItemEvent = new PlayerEvent.BeforePickUpItem(player, (ItemEntity) ((Object) this), this.getItem());
        EventManager.invoke(playerBeforePickUpItemEvent);

        if (!playerBeforePickUpItemEvent.canPickUp()) {
            ci.cancel();
        }
    }

    @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;take(Lnet/minecraft/world/entity/Entity;I)V"))
    private void enchantedbooklib$pickUp(Player player, CallbackInfo ci) {
        if (this.itemCache != null) {
            EventManager.invoke(new PlayerEvent.PickUpItem(player, (ItemEntity) ((Object) this), this.getItem()));
        }

        this.itemCache = null;
    }
}
