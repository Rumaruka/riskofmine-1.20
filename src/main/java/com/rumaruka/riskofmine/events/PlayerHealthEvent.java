package com.rumaruka.riskofmine.events;

import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class PlayerHealthEvent extends PlayerEvent {
    public PlayerHealthEvent(Player player) {
        super(player);
    }

    @Cancelable
    public static class PickupHealth extends PlayerHealthEvent {
        private final HealthOrbEntity orb;

        public PickupHealth(Player player, HealthOrbEntity orb) {
            super(player);
            this.orb = orb;
        }

        public HealthOrbEntity getOrb() {
            return orb;
        }
    }
}
