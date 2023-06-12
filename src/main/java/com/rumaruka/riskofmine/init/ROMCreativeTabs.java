package com.rumaruka.riskofmine.init;


import com.rumaruka.riskofmine.RiskOfMine;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static com.rumaruka.riskofmine.RiskOfMine.rl;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ROMCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> tabROM = CREATIVE_MODE_TABS.register("riskofmine",() -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(ROMItems.ARMOR_PIERCING_ROUNDS))
            .title(Component.translatable("itemGroup." + RiskOfMine.MODID))
            .displayItems((p_270258_, p_259752_) ->  {
                ROMItems.getAllItem().forEach(p_259752_::accept);

            }).build()
    );

    public static void setup() {
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}
