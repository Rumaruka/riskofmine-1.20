package com.rumaruka.riskofmine.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class ROMSounds {

    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static final RegistryObject<SoundEvent> ROM_CHEST_OPEN = REGISTER.register("riskofmine.block.open_chest",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.block.open_chest")));
    public static final RegistryObject<SoundEvent> ROM_CHEST_NOT_MONEY = REGISTER.register("riskofmine.block.not_money",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.block.not_money")));
    public static final RegistryObject<SoundEvent> UI_VOID_REPLACE_ITEM = REGISTER.register("riskofmine.ui.void_replace",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.ui.void_replace")));

    public static final RegistryObject<SoundEvent> PROC_MT_SPAWN = REGISTER.register("riskofmine.proc.mt_spawn",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.proc.mt_spawn")));
    public static final RegistryObject<SoundEvent> PROC_MT_IMPACT = REGISTER.register("riskofmine.proc.mt_impact",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.proc.mt_impact")));
    public static final RegistryObject<SoundEvent> ROM_PLAYER_FEATHER = REGISTER.register("riskofmine.player.feather_1",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.player.feather_1")));
    public static final RegistryObject<SoundEvent> ROM_PLAYER_LEVEL_UP = REGISTER.register("riskofmine.player.level_up",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.player.level_up")));

    public static final RegistryObject<SoundEvent> ROM_SCRAPPER_WORK = REGISTER.register("riskofmine.proc.scrapper",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.proc.scrapper")));
}
