package com.github.command17.enchantedbooklib.api.events.entity;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.event.ICancelableEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public abstract class LivingEntityEvent extends Event {
    private final LivingEntity entity;

    public LivingEntityEvent(LivingEntity entity) {
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public static class Death extends LivingEntityEvent implements ICancelableEvent {
        private final DamageSource source;

        public Death(LivingEntity entity, DamageSource source) {
            super(entity);
            this.source = source;
        }

        public DamageSource getSource() {
            return source;
        }
    }

    public static class Damage extends LivingEntityEvent implements ICancelableEvent {
        private final DamageSource source;
        private final float amount;

        public Damage(LivingEntity entity, DamageSource source, float amount) {
            super(entity);
            this.source = source;
            this.amount = amount;
        }

        public DamageSource getSource() {
            return source;
        }

        public float getAmount() {
            return amount;
        }
    }
}
