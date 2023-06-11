package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.effect.BleedEffect;
import com.rumaruka.riskofmine.common.effect.NullifiedEffect;
import com.rumaruka.riskofmine.common.effect.StunEffect;
import com.rumaruka.riskofmine.common.entity.elite_effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

public class ROMEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    /**
     * POTIONS FOR TESTING EFFECTS!
     */
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);


    public static final RegistryObject<MobEffect> STUN = EFFECTS.register("stun", () -> new StunEffect(MobEffectCategory.HARMFUL, 0x2A2D2E));
    public static final RegistryObject<MobEffect> BLEED = EFFECTS.register("bleed", () -> new BleedEffect(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> NULLIFIED = EFFECTS.register("nullified", () -> new NullifiedEffect(MobEffectCategory.BENEFICIAL, 5646433));


    public static final RegistryObject<MobEffect> BLAZING = EFFECTS.register("blazing", () -> new BlazingMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> CELESTINE = EFFECTS.register("celestine", () -> new CelestineMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> GLACIAL = EFFECTS.register("glacial", () -> new GlacialMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> MALACHITE = EFFECTS.register("malachite", () -> new MalachiteMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> MALACHITE_ELITES = EFFECTS.register("malachite_elites", () -> new MalachiteElitesMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> OVERLOADING = EFFECTS.register("overloading", () -> new OverloadingMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> OVERLOADING_ELITES = EFFECTS.register("overloading_elites", () -> new OverloadingMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> PERFECTED = EFFECTS.register("perfected", () -> new PerfectedMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final RegistryObject<MobEffect> MENDING = EFFECTS.register("mending", () -> new MendingMobs(MobEffectCategory.BENEFICIAL, 5646433));
}
