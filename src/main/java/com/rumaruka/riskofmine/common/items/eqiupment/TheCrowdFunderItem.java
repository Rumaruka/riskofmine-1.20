package com.rumaruka.riskofmine.common.items.eqiupment;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.common.cap.Money;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TheCrowdFunderItem extends EquipmentBase{
    public TheCrowdFunderItem( ) {
        super(Category.DAMAGE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        Money money = Money.of(pPlayer);
        if (money!=null){
            money.removeMoney(1);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
