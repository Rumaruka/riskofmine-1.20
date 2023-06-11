package com.rumaruka.riskofmine.common.config;

import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.common.config.Config;
import ru.timeconqueror.timecore.api.common.config.ConfigSection;
import ru.timeconqueror.timecore.api.common.config.IQuickConfigValue;
import ru.timeconqueror.timecore.api.common.config.ImprovedConfigBuilder;

public class ConfigGeneral extends Config {

    public IQuickConfigValue<Integer> sizeCurio;
    public IQuickConfigValue<Integer> cooldownEq;
    public IQuickConfigValue<Long> TIME_UPDATE_TIMER; //DEFAULT after 15 minute
    public IQuickConfigValue<Integer> priceSmallChest;
    public ConfigGeneral(@NotNull Type type, @NotNull String key, @Nullable String comment) {
        super(type, key, comment);
    }
    @Override
    public void setup(ImprovedConfigBuilder builder) {

        sizeCurio = builder.optimized(
                builder.comment("Curio Size Slot.\"+\"NEED RESTART GAME!")
                        .defineInRange("Slot size",4,1,9)

        );
        cooldownEq = builder.optimized(
                builder.comment("Cooldowns Equipment Items")
                       .defineInRange("Cooldowns",5000,1000,Integer.MAX_VALUE)

        );

        priceSmallChest = builder.optimized(
                builder.comment("Payment for Open Small Chest")
                        .defineInRange("Payment Small Chest: ",10,10,Integer.MAX_VALUE)

        );

       TIME_UPDATE_TIMER =builder.optimized(
               builder.comment("Updated Timer")
                       .defineInRange("After minute: ",15,5,Long.MAX_VALUE)

       );

    }
}
