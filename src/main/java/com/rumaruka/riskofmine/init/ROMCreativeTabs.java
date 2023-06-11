package com.rumaruka.riskofmine.init;


import com.rumaruka.riskofmine.RiskOfMine;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

@AutoRegistrable.Entries("creative_mode_tab")
public class ROMCreativeTabs {
    @AutoRegistrable
    private static final SimpleVanillaRegister<CreativeModeTab> REGISTER = new SimpleVanillaRegister<>(BuiltInRegistries.CREATIVE_MODE_TAB, MODID);

    public static CreativeModeTab MAIN;

    @AutoRegistrable.Init
    private static void register() {
        REGISTER.register("main", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + RiskOfMine.MODID))
                .icon(() -> new ItemStack(ROMItems.ARMOR_PIERCING_ROUNDS))
                .displayItems((pEnabledFeatures, pOutput) -> {
                    ROMItems.getAllItem().forEach(pOutput::accept);
                }).build()
        );
    }
}
