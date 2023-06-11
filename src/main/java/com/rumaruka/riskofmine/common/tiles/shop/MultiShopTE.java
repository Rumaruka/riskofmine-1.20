package com.rumaruka.riskofmine.common.tiles.shop;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.ChestShopInventory;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class MultiShopTE extends GenericShopTE {

    public MultiShopTE(BlockPos blockPos, BlockState blockState) {
        super(ROMTiles.MULTI_SHOP, blockPos, blockState, Chest.MULTI_SHOP, ROMBlocks.MULTI_SHOP);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestShopInventory.createMultiShopContainer(pContainerId, pInventory, this);
    }
}
