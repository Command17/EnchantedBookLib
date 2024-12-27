package com.github.command17.enchantedbooklib.mixin.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.input.RawMouseClickEvent;
import com.github.command17.enchantedbooklib.api.client.events.input.RawMouseScrollEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(
            method = "onPress",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;getOverlay()Lnet/minecraft/client/gui/screens/Overlay;",
                    ordinal = 0
            ),
            cancellable = true
    )
    public void enchantedbooklib$onRawMouseClickPre(long windowPointer, int button, int action, int mods, CallbackInfo ci) {
        if (!ci.isCancelled()) {
            RawMouseClickEvent.Pre rawMouseClickEvent = new RawMouseClickEvent.Pre(this.minecraft, button, action, mods);
            EventManager.invoke(rawMouseClickEvent);

            if (rawMouseClickEvent.isCanceled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "onPress", at = @At("RETURN"), cancellable = true)
    public void enchantedbooklib$onRawMouseClickPost(long windowPointer, int button, int action, int mods, CallbackInfo ci) {
        if (windowPointer == this.minecraft.getWindow().getWindow()) {
            RawMouseClickEvent.Pre rawMouseClickEvent = new RawMouseClickEvent.Pre(this.minecraft, button, action, mods);
            EventManager.invoke(rawMouseClickEvent);

            if (rawMouseClickEvent.isCanceled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "onScroll",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z",
                    ordinal = 0
            ),
            cancellable = true
    )
    public void enchantedbooklib$onRawMouseScroll(long windowPointer, double xOffset, double yOffset, CallbackInfo ci, @Local(ordinal = 3) double amountX, @Local(ordinal = 4) double amountY) {
        RawMouseScrollEvent rawMouseScrollEvent = new RawMouseScrollEvent(this.minecraft, amountX, amountY);
        EventManager.invoke(rawMouseScrollEvent);

        if (rawMouseScrollEvent.isCanceled()) {
            ci.cancel();
        }
    }
}
