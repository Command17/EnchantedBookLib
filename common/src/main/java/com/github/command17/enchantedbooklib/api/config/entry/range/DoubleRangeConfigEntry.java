package com.github.command17.enchantedbooklib.api.config.entry.range;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import com.github.command17.enchantedbooklib.api.config.entry.DoubleConfigEntry;
import net.minecraft.util.Mth;

public class DoubleRangeConfigEntry extends DoubleConfigEntry {
    private final double minValue;
    private final double maxValue;

    public DoubleRangeConfigEntry(double value, double minValue, double maxValue) {
        super(value);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        super.read(definition, json);
        this.setValue(Mth.clamp(this.getValue(), this.minValue, this.maxValue));
    }
}
