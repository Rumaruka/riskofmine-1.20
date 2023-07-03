package com.rumaruka.riskofmine.common.blocks;

import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ScrapperBlock extends Block {
    public ScrapperBlock() {
        super(Properties.of());
    }

    @Override
    public InteractionResult use(BlockState state_, Level world_, BlockPos pos_, Player player_, InteractionHand hand_, BlockHitResult hit_) {
        if (!world_.isClientSide()) {
            if (ROMUtils.isCommon(player_)) {
                ItemEntity itemEntity = new ItemEntity(world_,pos_.getX(),pos_.getY(),pos_.getZ(),new ItemStack(ROMItems.COMMON_ITEM_SCRAP,1));
                world_.addFreshEntity(itemEntity);

                return InteractionResult.SUCCESS;
            }
            if (ROMUtils.isUnCommon(player_)) {
                ItemEntity itemEntity = new ItemEntity(world_,pos_.getX(),pos_.getY(),pos_.getZ(),new ItemStack(ROMItems.UNCOMMON_ITEM_SCRAP,1));
                world_.addFreshEntity(itemEntity);

                return InteractionResult.SUCCESS;
            }
        }


        return InteractionResult.CONSUME;
    }



}
