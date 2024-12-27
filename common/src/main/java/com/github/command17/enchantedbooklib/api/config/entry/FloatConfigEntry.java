package com.github.command17.enchantedbooklib.api.config.entry;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.network.FriendlyByteBuf;

public class FloatConfigEntry extends ConfigEntry<Float> {
    public FloatConfigEntry(float value) {
        super(value);
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        this.setValue(json.getFloat(definition.name(), this.getDefaultValue()));
    }

    @Override
    public void write(Entry definition, JsonObject json) {
        json.putDefault(definition.name(), this.getValue(), definition.comment());
    }

    @Override
    public void readBuf(FriendlyByteBuf buf) {
        this.setSyncedValue(buf.readFloat());
    }

    @Override
    public void writeBuf(FriendlyByteBuf buf) {
        buf.writeFloat(this.getValue());
    }
}
