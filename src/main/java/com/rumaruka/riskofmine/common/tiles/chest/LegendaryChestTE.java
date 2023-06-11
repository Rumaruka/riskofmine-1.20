package com.rumaruka.riskofmine.common.tiles.chest;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.ChestInventory;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class LegendaryChestTE extends GenericChestTE {
    public LegendaryChestTE(BlockPos blockPos, BlockState blockState) {
        super(ROMTiles.LEGENDARY_CHEST, blockPos, blockState, Chest.LEGENDARY, ROMBlocks.LEGENDARY_CHEST);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestInventory.createLegendaryContainer(pContainerId, pInventory, this);
    }
}
