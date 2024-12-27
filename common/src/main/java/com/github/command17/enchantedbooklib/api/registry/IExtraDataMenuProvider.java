package com.github.command17.enchantedbooklib.api.registry;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.MenuProvider;

public interface IExtraDataMenuProvider extends MenuProvider {
    void writeExtraData(FriendlyByteBuf buf);
}
