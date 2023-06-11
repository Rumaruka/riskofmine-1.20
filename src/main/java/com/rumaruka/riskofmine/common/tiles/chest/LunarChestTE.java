package com.rumaruka.riskofmine.common.tiles.chest;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.ChestInventory;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class LunarChestTE extends GenericChestTE {
    public LunarChestTE(BlockPos blockPos, BlockState blockState) {
        super(ROMTiles.LUNAR_CHEST, blockPos, blockState, Chest.LUNAR, ROMBlocks.LUNAR_CHEST);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestInventory.createLunarContainer(pContainerId, pInventory, this);
    }
}
