package com.github.command17.enchantedbooklib.api.config.entry.range;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.entry.FloatConfigEntry;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.util.Mth;

public class FloatRangeConfigEntry extends FloatConfigEntry {
    private final float minValue;
    private final float maxValue;

    public FloatRangeConfigEntry(float value, float minValue, float maxValue) {
        super(value);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        super.read(definition, json);
        this.setValue(Mth.clamp(this.getValue(), this.minValue, this.maxValue));
    }
}
