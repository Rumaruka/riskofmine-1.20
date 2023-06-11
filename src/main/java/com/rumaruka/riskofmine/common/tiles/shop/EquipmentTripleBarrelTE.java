package com.rumaruka.riskofmine.common.tiles.shop;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.ChestShopInventory;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class EquipmentTripleBarrelTE extends GenericShopTE {

    public EquipmentTripleBarrelTE(BlockPos blockPos, BlockState blockState) {
        super(ROMTiles.EQUIPMENT_TRIPLE_BARREL, blockPos, blockState, Chest.EQUIPMENT_TRIPLE_BARREL, ROMBlocks.EQUIPMENT_TRIPLE_BARREL);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestShopInventory.createEquipmentTripleBarrelContainer(pContainerId, pInventory, this);
    }
}
