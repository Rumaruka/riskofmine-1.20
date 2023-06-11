package com.rumaruka.riskofmine.common.blocks.chest;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.common.tiles.chest.GenericChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.SmallChestTE;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SmallChestBlock extends GenericChestBlock {
    public SmallChestBlock() {
        super(Properties.of().strength(5, 5), ROMTiles.SMALL_CHEST, Chest.SMALL);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Money money = Money.of(player);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GenericChestTE) {
                if (money != null && money.getCurrentMoney() >= ROMConfig.GENERAL.priceSmallChest.get()) {
                    money.consumeMoney(player, ROMConfig.GENERAL.priceSmallChest.get());
                    money.detectAndSendChanges();
                    player.openMenu((GenericChestTE) blockEntity);
                    player.awardStat(Stats.OPEN_CHEST);
                    PiglinAi.angerNearbyPiglins(player, true);
                } else if (money != null && money.getCurrentMoney() < ROMConfig.GENERAL.priceSmallChest.get()) {
                    level.playSound(null, blockPos, ROMSounds.ROM_CHEST_NOT_MONEY.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
                    player.displayClientMessage(Component.translatable("riskofmine.not_money" + ROMConfig.GENERAL.priceSmallChest.get()), true);

                }


            }
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SmallChestTE(pPos, pState);
    }
}
