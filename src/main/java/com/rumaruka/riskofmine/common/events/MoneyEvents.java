package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.common.cap.Money;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MoneyEvents {
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level level = livingEntity.level();
            Money money = Money.of(player);
            if (!level.isClientSide()) {
                if (money != null) {
                    money.addMoney(10);
                    money.detectAndSendChanges();
                }
            }
        }
        if (event.getSource().getEntity() instanceof AmbientCreature livingEntity && event.getEntity() instanceof ServerPlayer player) {
            Level world = player.level();
            Money money = Money.of(player);
            if (!world.isClientSide) {
                if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    return;
                } else {
                    if (money != null) {

                        money.setMoney(0);
                        money.detectAndSendChanges();

                    }
                }


            }
        }
    }

}
