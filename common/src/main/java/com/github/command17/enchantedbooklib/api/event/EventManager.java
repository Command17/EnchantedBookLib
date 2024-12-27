package com.github.command17.enchantedbooklib.api.event;

import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.google.common.collect.ArrayListMultimap;
import net.jodah.typetools.TypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public final class EventManager {
    private EventManager() {}

    private static final ArrayListMultimap<Class<? extends Event>, Consumer<? extends Event>> LISTENERS = ArrayListMultimap.create();

    public static<T extends Event> void registerListener(Class<T> eventClass, Consumer<T> listener) {
        LISTENERS.get(eventClass).add(listener);
    }

    @SuppressWarnings("unchecked")
    public static<T extends Event> void registerListener(Consumer<T> listener) {
        Class<T> eventClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, listener.getClass());

        if ((Class<?>) eventClass == TypeResolver.Unknown.class) {
            throw new IllegalStateException("Unknown event for listener '" + listener + "'!");
        }

        registerListener(eventClass, listener);
    }

    @SuppressWarnings("unchecked")
    public static void registerListeners(Object object) {
        boolean isStatic = object.getClass() == Class.class;

        Class<?> clazz = isStatic ? (Class<?>) object : object.getClass();

        for (Method method: clazz.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) == isStatic && method.isAnnotationPresent(EventListener.class)) {
                method.setAccessible(true);

                if (method.getParameterCount() != 1) {
                    throw new IllegalStateException("Too many parameters for method '" + method + "' annotated with @EventListener!");
                }

                Class<?> eventClass = method.getParameterTypes()[0];

                if (Event.class.isAssignableFrom(eventClass)) {
                    registerListener((Class<? extends Event>) eventClass, (event) -> {
                        try {
                            method.invoke(object, event);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    throw new IllegalStateException("Unknown event for method '" + method + "' annotated with @EventListener!");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static<T extends Event> void invoke(T event) {
        for (var listener: LISTENERS.get(event.getClass())) {
            ((Consumer<T>) listener).accept(event);

            if (event.canceled) {
                break;
            }
        }
    }
}
