package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.cap.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.api.CapabilityManagerAPI;
import ru.timeconqueror.timecore.api.registry.CapabilityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ROMCap {


    @AutoRegistrable
    private static final CapabilityRegister REGISTER = new CapabilityRegister(MODID);

    public static final Capability<Money> MONEY = REGISTER.register(Money.class);
    public static final Capability<Lunar> LUNAR = REGISTER.register(Lunar.class);
    public static final Capability<Timer> TIMER = REGISTER.register(Timer.class);
    public static final Capability<Shields> SHIELDS = REGISTER.register(Shields.class);
    public static final Capability<Barrier> BARRIER = REGISTER.register(Barrier.class);

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> CapabilityManagerAPI.registerStaticCoffeeAttacher(CapabilityOwner.ENTITY, MONEY, entity -> entity instanceof Player, entity -> new Money(((Player) entity))));
        CapabilityManagerAPI.makePlayerCapSyncOnJoin(entity -> {
            Money cap = Money.of(entity);
            if (cap != null) {
                cap.sendAllData();
            }
        });

        event.enqueueWork(() -> CapabilityManagerAPI.registerStaticCoffeeAttacher(CapabilityOwner.ENTITY, SHIELDS, entity -> entity instanceof LivingEntity, entity -> new Shields((LivingEntity) entity)));
        CapabilityManagerAPI.makePlayerCapSyncOnJoin(entity -> {
            Shields cap = Shields.of(entity);
            if (cap != null) {
                cap.sendAllData();
            }
        });

        event.enqueueWork(() -> CapabilityManagerAPI.registerStaticCoffeeAttacher(CapabilityOwner.ENTITY, BARRIER, entity -> entity instanceof Player, entity -> new Barrier((Player) entity)));
        CapabilityManagerAPI.makePlayerCapSyncOnJoin(entity -> {
            Barrier cap = Barrier.of(entity);
            if (cap != null) {
                cap.sendAllData();
            }
        });

        event.enqueueWork(() -> CapabilityManagerAPI.registerStaticCoffeeAttacher(CapabilityOwner.ENTITY, LUNAR, entity -> entity instanceof Player, entity -> new Lunar(((Player) entity))));
        CapabilityManagerAPI.makePlayerCapSyncOnJoin(entity -> {
            Lunar cap = Lunar.of(entity);
            if (cap != null) {
                cap.sendAllData();
            }
        });
        event.enqueueWork(() -> CapabilityManagerAPI.registerStaticCoffeeAttacher(CapabilityOwner.ENTITY, TIMER, entity -> entity instanceof Player, entity -> new Timer(((Player) entity))));
        CapabilityManagerAPI.makePlayerCapSyncOnJoin(entity -> {
            Timer cap = Timer.of(entity);
            if (cap != null) {
                cap.sendAllData();
            }
        });
    }

}
