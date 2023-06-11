package com.rumaruka.riskofmine.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

public class ROMParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final RegistryObject<SimpleParticleType> FOCUS_CRYSTAL = PARTICLES.register("focus_crystal", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> STUN_PARTICLES = PARTICLES.register("stun", () -> new SimpleParticleType(true));
}
