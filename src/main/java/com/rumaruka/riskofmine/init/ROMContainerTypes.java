package com.rumaruka.riskofmine.init;


import com.rumaruka.riskofmine.common.inventory.ChestInventory;
import com.rumaruka.riskofmine.common.inventory.ChestShopInventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static ru.timeconqueror.timecore.api.util.Hacks.promise;

@AutoRegistrable.Entries("menu")
public class ROMContainerTypes {
    @AutoRegistrable
    private static final SimpleVanillaRegister<MenuType<?>> REGISTER = new SimpleVanillaRegister<>(ForgeRegistries.MENU_TYPES, MODID);

    public static MenuType<ChestInventory> SMALL_CHEST = promise();
    public static MenuType<ChestInventory> LARGE_CHEST = promise();
    public static MenuType<ChestInventory> LEGENDARY_CHEST = promise();
    public static MenuType<ChestInventory> LUNAR_CHEST = promise();

    public static MenuType<ChestShopInventory> MULTI_SHOP = promise();
    public static MenuType<ChestShopInventory> EQUIPMENT_TRIPLE_BARREL = promise();

    @AutoRegistrable.Init
    private static void register() {
        REGISTER.register("small_chest", () -> IForgeMenuType.create((windowId, inv, data) -> ChestInventory.createCommonContainer(windowId, inv)));
        REGISTER.register("large_chest", () -> IForgeMenuType.create((windowId, inv, data) -> ChestInventory.createLargeContainer(windowId, inv)));
        REGISTER.register("legendary_chest", () -> IForgeMenuType.create((windowId, inv, data) -> ChestInventory.createLegendaryContainer(windowId, inv)));
        REGISTER.register("lunar_chest", () -> IForgeMenuType.create((windowId, inv, data) -> ChestInventory.createLunarContainer(windowId, inv)));
        REGISTER.register("equipment_triple_barrel", () -> IForgeMenuType.create((windowId, inv, data) -> ChestShopInventory.createEquipmentTripleBarrelContainer(windowId, inv)));
        REGISTER.register("multi_shop", () -> IForgeMenuType.create((windowId, inv, data) -> ChestShopInventory.createMultiShopContainer(windowId, inv)));
    }
}
