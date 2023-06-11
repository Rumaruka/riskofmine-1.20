package com.rumaruka.riskofmine.common.blocks;

import com.rumaruka.riskofmine.common.tiles.WarbannerTE;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class WarbannerBlock extends BaseEntityBlock {
    public WarbannerBlock() {
        super(Properties.of());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new WarbannerTE(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createWarbannerTicker(pLevel, pBlockEntityType, ROMTiles.WARBANNER_BLOCK);
    }

    @javax.annotation.Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createWarbannerTicker(Level pLevel, BlockEntityType<T> pServerType, BlockEntityType<? extends WarbannerTE> pClientType) {
        return pLevel.isClientSide ? null : createTickerHelper(pServerType, pClientType, WarbannerTE::tick);
    }
}
