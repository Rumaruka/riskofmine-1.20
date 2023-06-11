package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BarrierEvents {


    @SubscribeEvent
    public static void onDeathEntity(LivingDeathEvent event) {
        /**
         Player kill Entity
         */
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level world = livingEntity.level();
            Barrier barrier = Barrier.of(player);
            if (!world.isClientSide()) {
                if (barrier != null) {
                    if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.TOPAZ_BROOCH))) {
                        barrier.addBarrier(10*ROMUtils.counting(player, new ItemStack(ROMItems.TOPAZ_BROOCH)));
                        barrier.detectAndSendChanges();
                    }


                }
            }

        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onDamagePlayerWithBarrier(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();


        if (entity instanceof Player player) {
            Barrier barrier = Barrier.of(player);
            if (barrier != null) {
                if (barrier.hasBarrier()){
                    event.setCanceled(true);
                    barrier.detectAndSendChanges();
                }

            }


        }


    }

    @SubscribeEvent
    public static void onTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            LivingEntity livingEntity = event.getEntity();
            Level world = livingEntity.level();
            Barrier barrier = Barrier.of(player);
            if (!world.isClientSide()) {
                if (barrier != null) {
                    if (barrier.hasBarrier()&&player.tickCount %10==0) {
                        barrier.removeBarrier(1);
                    }
                    barrier.detectAndSendChanges();
                }
            }
        }

    }
}
