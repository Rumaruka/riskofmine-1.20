package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.common.cap.Timer;
import com.rumaruka.riskofmine.common.entity.MalachiteUrchinsEntity;
import com.rumaruka.riskofmine.init.ROMEffects;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
@Mod.EventBusSubscriber
public class ElitesEvents {

    @SubscribeEvent
    public static void addEffect(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Player player = ROMUtils.getPlayer();
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (player != null) {
                Timer timer = Timer.of(player);
                if (timer != null && !(entity instanceof Player)) {
                    if (timer.getCurrentTime() > 10) {
                        entity.addEffect(new MobEffectInstance(ROMEffects.MENDING.get()));

                    }
                }
            }

        }

    }

    @SubscribeEvent
    public static void addFunctionForEffectsTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();


        Level level = entity.level();
        if (!level.isClientSide()) {
            if (entity.hasEffect(ROMEffects.MENDING.get())) {
                List<LivingEntity> entities = level.getEntitiesOfClass(
                        LivingEntity.class, new AABB(entity.getX() + 2, entity.getY() + 1, entity.getZ() + 2, entity.getX() - 2, entity.getY() + 2, entity.getZ() - 2)
                );
                for (LivingEntity anotherEntity : entities) {

                    if (!(entity instanceof Player)) {


                        anotherEntity.heal(0.01f);


                    }


                }
            }
            Shields mainShields = Shields.of(entity);
            if (mainShields != null) {
                ((IOverloading) entity).setOverloading(entity.hasEffect(ROMEffects.OVERLOADING.get())||mainShields.getCurrentShields() > 0);

            }

            if (entity.hasEffect(ROMEffects.MALACHITE_ELITES.get())) {

                if (entity.tickCount % 25 == 0) {
                    level.addFreshEntity(new MalachiteUrchinsEntity(level, entity.getX() + 3, entity.getY(), entity.getZ() + 3));

                }
            }
        }
    }


    @SubscribeEvent
    public static void addFunctionForEffectsAttack(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (entity.hasEffect(ROMEffects.BLAZING.get())) {
                ROMUtils.getPlayer().setRemainingFireTicks(1);

            }


        }
    }

    @SubscribeEvent
    public static void removeHealthIfEffects(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(ROMEffects.MALACHITE.get())) {
            event.setCanceled(true);
        }

    }


}



