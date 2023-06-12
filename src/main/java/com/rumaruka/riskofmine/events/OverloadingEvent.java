package com.rumaruka.riskofmine.events;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber
public class OverloadingEvent {
    @SubscribeEvent
    public static void onTrackedEntity(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        Player entity = event.getEntity();
        if (((IOverloading) target).isOverloading()) {
            ROMNetwork.network.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) entity), new OverloadingPacket(target.getId(), true));

        }
    }
}
