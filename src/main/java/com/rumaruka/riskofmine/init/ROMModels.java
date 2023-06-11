package com.rumaruka.riskofmine.init;

import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.api.registry.TimeModelRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.client.render.model.InFileLocation;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ROMModels {
    @AutoRegistrable
    private static final TimeModelRegister REGISTER = new TimeModelRegister(MODID);
    public static InFileLocation SMALL_CHEST = REGISTER.register("models/tile/small_chest.json");
    public static InFileLocation STICKY_BOMB = REGISTER.register("models/entity/sticky_bomb.json");
    public static InFileLocation MULTI_SHOP_OPEN = REGISTER.register("models/tile/multi_shop_open.json");
}
