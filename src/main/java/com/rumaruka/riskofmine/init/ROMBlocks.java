package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.blocks.ScrapperBlock;
import com.rumaruka.riskofmine.common.blocks.WarbannerBlock;
import com.rumaruka.riskofmine.common.blocks.chest.LargeChestBlock;
import com.rumaruka.riskofmine.common.blocks.chest.LegendaryChestBlock;
import com.rumaruka.riskofmine.common.blocks.chest.LunarChestBlock;
import com.rumaruka.riskofmine.common.blocks.chest.SmallChestBlock;
import com.rumaruka.riskofmine.common.blocks.shop.EquipmentTripleBarrelBlock;
import com.rumaruka.riskofmine.common.blocks.shop.MultiShopBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static ru.timeconqueror.timecore.api.util.Hacks.promise;

@AutoRegistrable.Entries("block")
public class ROMBlocks {
    public static SmallChestBlock SMALL_CHEST = promise();
    public static LargeChestBlock LARGE_CHEST = promise();
    public static LegendaryChestBlock LEGENDARY_CHEST = promise();
    public static LunarChestBlock LUNAR_CHEST = promise();
    public static MultiShopBlock MULTI_SHOP = promise();

    public static WarbannerBlock WARBANNER_BLOCK = promise();
    public static ScrapperBlock SCRAPPER = promise();
    public static EquipmentTripleBarrelBlock EQUIPMENT_TRIPLE_BARREL = promise();

    @AutoRegistrable
    private static final BlockRegister REGISTER = new BlockRegister(MODID);

    @AutoRegistrable.Init
    private static void register() {
        REGISTER.register("small_chest", SmallChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
        REGISTER.register("large_chest", LargeChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
        REGISTER.register("legendary_chest", LegendaryChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
        REGISTER.register("lunar_chest", LunarChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
        REGISTER.register("warbanner_block", WarbannerBlock::new).oneVarStateAndCubeAllModel();
        REGISTER.register("scrapper", ScrapperBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());


        REGISTER.register("multi_shop", MultiShopBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
        REGISTER.register("equipment_triple_barrel", EquipmentTripleBarrelBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());


    }


}
