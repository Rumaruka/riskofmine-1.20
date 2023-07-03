package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.client.tesr.MultiShopTESR;
import com.rumaruka.riskofmine.client.tesr.SmallChestTESR;
import com.rumaruka.riskofmine.common.tiles.WarbannerTE;
import com.rumaruka.riskofmine.common.tiles.chest.LargeChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.LegendaryChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.LunarChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.SmallChestTE;
import com.rumaruka.riskofmine.common.tiles.shop.EquipmentTripleBarrelTE;
import com.rumaruka.riskofmine.common.tiles.shop.MultiShopTE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import ru.timeconqueror.timecore.api.registry.TileEntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static ru.timeconqueror.timecore.api.util.Hacks.promise;

@AutoRegistrable.Entries("block_entity_type")
public class ROMTiles {
    public static BlockEntityType<SmallChestTE> SMALL_CHEST = promise();
    public static BlockEntityType<LargeChestTE> LARGE_CHEST = promise();
    public static BlockEntityType<LegendaryChestTE> LEGENDARY_CHEST = promise();
    public static BlockEntityType<LunarChestTE> LUNAR_CHEST = promise();
    public static BlockEntityType<MultiShopTE> MULTI_SHOP = promise();
    public static BlockEntityType<EquipmentTripleBarrelTE> EQUIPMENT_TRIPLE_BARREL = promise();
    public static BlockEntityType<WarbannerTE> WARBANNER_BLOCK = promise();


    private static class Init {
        @AutoRegistrable
        private static final TileEntityRegister REGISTER = new TileEntityRegister(MODID);

        @AutoRegistrable.Init
        private static void register() {
            REGISTER.registerSingleBound("small_chest", SmallChestTE::new, () -> ROMBlocks.SMALL_CHEST).regCustomRenderer(() -> SmallChestTESR::new);
            REGISTER.registerSingleBound("large_chest", LargeChestTE::new, () -> ROMBlocks.LARGE_CHEST);
            REGISTER.registerSingleBound("legendary_chest", LegendaryChestTE::new, () -> ROMBlocks.LEGENDARY_CHEST);
            REGISTER.registerSingleBound("lunar_chest", LunarChestTE::new, () -> ROMBlocks.LUNAR_CHEST);
            REGISTER.registerSingleBound("warbanner_block", WarbannerTE::new, () -> ROMBlocks.WARBANNER_BLOCK);

            REGISTER.registerSingleBound("multi_shop", MultiShopTE::new, () -> ROMBlocks.MULTI_SHOP).regCustomRenderer(() -> MultiShopTESR::new);
            REGISTER.registerSingleBound("equipment_triple_barrel", EquipmentTripleBarrelTE::new, () -> ROMBlocks.EQUIPMENT_TRIPLE_BARREL);
        }
    }
}
