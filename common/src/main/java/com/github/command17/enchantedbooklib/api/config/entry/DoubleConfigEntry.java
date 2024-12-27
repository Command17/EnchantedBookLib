package com.github.command17.enchantedbooklib.api.config.entry;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.network.FriendlyByteBuf;

public class DoubleConfigEntry extends ConfigEntry<Double> {
    public DoubleConfigEntry(double value) {
        super(value);
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        this.setValue(json.getDouble(definition.name(), this.getDefaultValue()));
    }

    @Override
    public void write(Entry definition, JsonObject json) {
        json.putDefault(definition.name(), this.getValue(), definition.comment());
    }

    @Override
    public void readBuf(FriendlyByteBuf buf) {
        this.setSyncedValue(buf.readDouble());
    }

    @Override
    public void writeBuf(FriendlyByteBuf buf) {
        buf.writeDouble(this.getValue());
    }
}
