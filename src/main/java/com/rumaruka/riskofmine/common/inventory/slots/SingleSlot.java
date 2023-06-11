package com.rumaruka.riskofmine.common.inventory.slots;

import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SingleSlot extends Slot {

    public SingleSlot(Container inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof BaseCollectablesItem;
    }
}
