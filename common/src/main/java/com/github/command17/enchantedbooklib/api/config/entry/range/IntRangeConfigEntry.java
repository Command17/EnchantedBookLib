package com.github.command17.enchantedbooklib.api.config.entry.range;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import com.github.command17.enchantedbooklib.api.config.entry.IntConfigEntry;
import net.minecraft.util.Mth;

public class IntRangeConfigEntry extends IntConfigEntry {
    private final int minValue;
    private final int maxValue;

    public IntRangeConfigEntry(int value, int minValue, int maxValue) {
        super(value);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        super.read(definition, json);
        this.setValue(Mth.clamp(this.getValue(), this.minValue, this.maxValue));
    }
}
