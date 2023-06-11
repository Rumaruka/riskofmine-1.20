package com.rumaruka.riskofmine.common.items.uncommon;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class InfusionItem extends BaseCollectablesItem {
    public static final UUID uuid1 = UUID.randomUUID();

    public InfusionItem() {
        super(Types.UNCOMMON, Category.UTILITY, 64);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getMainHandItem();
        if (!pLevel.isClientSide()) {
            pPlayer.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(uuid1, "healthBoost", stack.getCount(), AttributeModifier.Operation.ADDITION));
            if (!pPlayer.isCreative() || !pPlayer.getAbilities().invulnerable) {
                stack.shrink(stack.getCount());
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
