package com.rumaruka.riskofmine.utils;

import com.rumaruka.riskofmine.client.fx.FocusCrystalFX;
import com.rumaruka.riskofmine.init.ROMParticles;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleUtils {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticle(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ROMParticles.FOCUS_CRYSTAL.get(), FocusCrystalFX.Factory::new);
        //   event.register(ROMParticles.STUN_PARTICLES.get(), StunFX.Factory::new);
    }
}
