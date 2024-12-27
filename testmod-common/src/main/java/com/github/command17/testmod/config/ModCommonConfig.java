package com.github.command17.testmod.config;

import com.github.command17.enchantedbooklib.api.config.ConfigData;
import com.github.command17.enchantedbooklib.api.config.annotation.Config;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import com.github.command17.enchantedbooklib.api.config.entry.ArrayConfigEntry;
import com.github.command17.enchantedbooklib.api.config.entry.BoolConfigEntry;
import com.github.command17.enchantedbooklib.api.config.entry.IntConfigEntry;
import com.github.command17.enchantedbooklib.api.config.entry.range.IntRangeConfigEntry;
import com.github.command17.testmod.TestMod;

@Config(name = TestMod.MOD_ID + "-common")
public class ModCommonConfig extends ConfigData {
    @Entry(name = "boolValue", comment = "A boolean value")
    public final BoolConfigEntry boolValue = new BoolConfigEntry(true);

    @Entry(name = "syncedBoolValue", comment = "A boolean value, but it's synchronised", synced = true)
    public final BoolConfigEntry syncedBoolValue = new BoolConfigEntry(true);

    @Entry(name = "intRange", comment = "An integer range value", category = "integer")
    public final IntRangeConfigEntry intRange = new IntRangeConfigEntry(0, -10, 10);

    @Entry(name = "intValue", comment = "An integer value", category = "integer")
    public final IntConfigEntry intValue = new IntConfigEntry(3);

    @Entry(name = "stringArray")
    public final ArrayConfigEntry<String> stringArray = new ArrayConfigEntry<>(new String[] {"hello", "world"});
}
