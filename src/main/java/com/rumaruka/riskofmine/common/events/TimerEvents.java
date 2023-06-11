package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.common.cap.Timer;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.init.ROMEffects;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TimerEvents {
    private static long time;

    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event){
        if (ROMConfig.GENERAL.TIME_UPDATE_TIMER.get() >0){
            if (time==0L){
                time = System.currentTimeMillis();
            }
            long minute = ROMConfig.GENERAL.TIME_UPDATE_TIMER.get() * 60L * 1000L;
            if (System.currentTimeMillis() - time > minute){
                Timer timer = Timer.of(ROMUtils.getPlayer());
                if (timer!=null){
                  //  timer.addTime(1);
                    timer.detectAndSendChanges();
                }
            }
        }
    }

}
