package com.github.command17.enchantedbooklib.api.event;

public interface ICancelableEvent {
    default void cancel() {
        this.setCanceled(true);
    }

    default void setCanceled(boolean canceled) {
        ((Event) this).canceled = canceled;
    }

    default boolean isCanceled() {
        return ((Event) this).canceled;
    }
}
