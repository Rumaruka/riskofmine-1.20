package com.rumaruka.riskofmine.common.tiles.chest;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.ChestInventory;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class SmallChestTE extends GenericChestTE {
    public SmallChestTE(BlockPos blockPos, BlockState blockState) {
        super(ROMTiles.SMALL_CHEST, blockPos, blockState, Chest.SMALL, ROMBlocks.SMALL_CHEST);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestInventory.createCommonContainer(pContainerId, pInventory, this);
    }
}
