package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.LivingEntityEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void enchantedbooklib$onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntityEvent.Death livingEntityDeathEvent = new LivingEntityEvent.Death((LivingEntity) ((Object) this), source);
        EventManager.invoke(livingEntityDeathEvent);

        if (livingEntityDeathEvent.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void enchantedbooklib$onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntityEvent.Damage livingEntityDamageEvent = new LivingEntityEvent.Damage((LivingEntity) ((Object) this), source, amount);
        EventManager.invoke(livingEntityDamageEvent);

        if (livingEntityDamageEvent.isCanceled()) {
            cir.setReturnValue(false);
        }
    }
}
