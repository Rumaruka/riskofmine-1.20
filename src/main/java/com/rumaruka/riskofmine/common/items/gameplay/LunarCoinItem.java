package com.rumaruka.riskofmine.common.items.gameplay;

import com.rumaruka.riskofmine.common.cap.Lunar;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LunarCoinItem extends GamePlayItem {
    public LunarCoinItem() {
        super();

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        Lunar lunar = Lunar.of(pPlayer);
        if (lunar != null) {
            lunar.addLunar(1);
            lunar.detectAndSendChanges();
        }

        itemStack.shrink(1);
        return InteractionResultHolder.success(itemStack);
    }
}
