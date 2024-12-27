package com.github.command17.enchantedbooklib.api.util;

import net.fabricmc.api.EnvType;

public enum EnvSide {
    CLIENT,
    SERVER;

    public static EnvSide fromPlatformSpecific(Object env) {
        return env == EnvType.CLIENT ? CLIENT : SERVER;
    }

    public static EnvType toPlatformSpecific(EnvSide env) {
        return env == CLIENT ? EnvType.CLIENT : EnvType.SERVER;
    }
}
