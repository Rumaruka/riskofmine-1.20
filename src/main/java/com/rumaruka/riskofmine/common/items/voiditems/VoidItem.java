package com.rumaruka.riskofmine.common.items.voiditems;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.IVoidItem;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.world.item.ItemStack;

public class VoidItem extends BaseCollectablesItem implements IVoidItem {
    public VoidItem(Category category, int size) {
        super(Types.VOID, category, size);
    }

    @Override
    public void replaceItem(ItemStack uncorruted, ItemStack corruted) {
        uncorruted.shrink(1);
        corruted.shrink(-1);

    }
}
