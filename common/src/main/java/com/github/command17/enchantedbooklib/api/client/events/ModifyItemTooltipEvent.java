package com.github.command17.enchantedbooklib.api.client.events;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ModifyItemTooltipEvent extends Event {
    private final ItemStack stack;
    private final Item.TooltipContext context;
    private final List<Component> tooltips;
    private final TooltipFlag flag;

    public ModifyItemTooltipEvent(ItemStack stack, Item.TooltipContext context, List<Component> tooltips, TooltipFlag flag) {
        this.stack = stack;
        this.context = context;
        this.tooltips = tooltips;
        this.flag = flag;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Item.TooltipContext getContext() {
        return context;
    }

    public List<Component> getTooltips() {
        return tooltips;
    }

    public TooltipFlag getFlag() {
        return flag;
    }
}
