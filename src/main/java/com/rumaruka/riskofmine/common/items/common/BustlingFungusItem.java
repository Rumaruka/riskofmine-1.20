package com.rumaruka.riskofmine.common.items.common;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.events.MovingHandler;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class BustlingFungusItem extends BaseCollectablesItem {
    public BustlingFungusItem() {
        super(Types.COMMON, Category.HEALING, 64);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.translatable("ror.alt.info"));
        if (Screen.hasAltDown()) {

            tooltip.add(Component.translatable("riskofmine.rarity" + ":"));
            tooltip.add(Component.translatable((getColor() + getTypeName())));
            tooltip.add(Component.translatable("riskofmine.category" + ":"));
            tooltip.add(Component.translatable((getColors() + getCategoryName())));
        }
        tooltip.add(Component.translatable("ror.shiftpress.info"));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("ror.bf.info"));
            tooltip.add(Component.translatable("[Stacks:" + pStack.getCount() + "]"));
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide()) {
            if (!MovingHandler.isMoving((Player) pEntity)) {
                ((Player) pEntity).heal((pStack.getCount() + 0.045f) / 20f);
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }


    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player) {
            if (!livingEntity.level.isClientSide()) {
                if (!MovingHandler.isMoving((ServerPlayer) livingEntity)) {

                    livingEntity.heal((stack.getCount() + 0.045f) / 20f);

                }
            }
        }
        super.curioTick(slotContext, stack);
    }


    @Override
    public boolean canSync(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
