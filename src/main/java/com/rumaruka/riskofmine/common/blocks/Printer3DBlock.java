package com.rumaruka.riskofmine.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collections;

public class Printer3DBlock extends Block {
   public Item priorityItem;
    public Printer3DBlock(Properties properties_, Item priority) {
        super(properties_);
    }

    @Override
    public InteractionResult use(BlockState state_, Level world_, BlockPos pos_, Player player_, InteractionHand hand_, BlockHitResult hit_) {
        if (!world_.isClientSide()){
            if (player_.getInventory().hasAnyOf(Collections.singleton(priorityItem))) {
                // Если у игрока есть предмет с приоритетом, используйте его
                ItemStack priorityStack = player_.getInventory().getItem(player_.getInventory().getSlotWithRemainingSpace(new ItemStack(priorityItem)));
                player_.getInventory().setItem(player_.getInventory().selected, priorityStack);
            } else {
                // Иначе используйте любой другой предмет
                for (int i = 0; i < player_.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player_.getInventory().getItem(i);
                    if (!stack.isEmpty() && stack.getItem() != priorityItem) {
                        player_.getInventory().setItem(player_.getInventory().selected, stack);
                        break;
                    }
                }
            }
        }
        
        return super.use(state_, world_, pos_, player_, hand_, hit_);
    }
}
