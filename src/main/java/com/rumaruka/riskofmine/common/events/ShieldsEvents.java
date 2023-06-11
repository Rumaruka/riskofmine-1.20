package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ShieldsEvents {


    @SubscribeEvent
    public static void onRegenShieldsWithShaped(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            Shields shields = Shields.of(player);

            if (shields != null) {
                if (player.getOffhandItem().getItem() == ROMItems.SHAPED_GLASS) {


                    if (!shields.isHurt() &&player.hurtTime==0) {

                        shields.setShields(16);
                    }
                    shields.detectAndSendChanges();

                }
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onDamagePlayerWithShield(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();


        if (entity instanceof Player player) {
            Shields shields = Shields.of(player);
            if (shields != null) {
                event.setCanceled(true);
                shields.removeShields(1);

                if (!shields.hasShields()) {

                    event.setCanceled(false);
                }
                shields.detectAndSendChanges();
            }


        }


    }



}
