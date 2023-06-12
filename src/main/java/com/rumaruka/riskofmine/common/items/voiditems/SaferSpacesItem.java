package com.rumaruka.riskofmine.common.items.voiditems;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.common.items.common.TougherTimesItem;
import com.rumaruka.riskofmine.init.ROMSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class SaferSpacesItem extends VoidItem {
    public SaferSpacesItem() {
        super(Category.HEALING, 15);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.getItem() instanceof TougherTimesItem) {
                    pLevel.playSound(null, pEntity.getX(), pEntity.getY(), pEntity.getZ(), ROMSounds.UI_VOID_REPLACE_ITEM.get(), SoundSource.MASTER, 2.0F, 1.0F);

                    replaceItem(itemStack, pStack);
                }
            }


        }

    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.getItem() instanceof TougherTimesItem) {
                    slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), ROMSounds.UI_VOID_REPLACE_ITEM.get(), SoundSource.MASTER, 2.0F, 1.0F);
                    replaceItem(itemStack, stack);
                }
            }

        }
    }
}
