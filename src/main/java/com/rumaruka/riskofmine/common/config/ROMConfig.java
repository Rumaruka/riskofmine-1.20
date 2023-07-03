package com.rumaruka.riskofmine.common.config;
import com.rumaruka.riskofmine.RiskOfMine;
import net.minecraftforge.fml.config.ModConfig;

import ru.timeconqueror.timecore.api.registry.ConfigRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
public class ROMConfig {
    public static final ConfigGeneral GENERAL = new ConfigGeneral(ModConfig.Type.COMMON, "riskofmine", "General Settings");

    @AutoRegistrable
    private static final ConfigRegister REGISTER = new ConfigRegister(RiskOfMine.MODID);

    static String resolve(String path) {
        return RiskOfMine.MODID + "/" + path;
    }

    @AutoRegistrable.Init
    private static void register() {
        REGISTER.register(GENERAL);

    }
}
