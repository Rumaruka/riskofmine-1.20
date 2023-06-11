package com.rumaruka.riskofmine.common.inventory;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.slots.SingleSlot;
import com.rumaruka.riskofmine.init.ROMContainerTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ChestInventory extends AbstractContainerMenu {
    private final Container container;

    private final Chest chestType;

    private ChestInventory(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory) {
        this(menuType, containerId, playerInventory, new SimpleContainer(Chest.NULL_SIZE.size), Chest.NULL_SIZE);
    }

    public static @NotNull ChestInventory createLunarContainer(int windowId, Inventory playerInventory) {
        return new ChestInventory(ROMContainerTypes.LUNAR_CHEST, windowId, playerInventory, new SimpleContainer(Chest.LUNAR.size), Chest.LUNAR);
    }

    public static ChestInventory createLunarContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new ChestInventory(ROMContainerTypes.LUNAR_CHEST, windowId, playerInventory, inventory, Chest.LUNAR);
    }


    public static @NotNull ChestInventory createLegendaryContainer(int windowId, Inventory playerInventory) {
        return new ChestInventory(ROMContainerTypes.LEGENDARY_CHEST, windowId, playerInventory, new SimpleContainer(Chest.LEGENDARY.size), Chest.LEGENDARY);
    }

    public static ChestInventory createLegendaryContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new ChestInventory(ROMContainerTypes.LEGENDARY_CHEST, windowId, playerInventory, inventory, Chest.LEGENDARY);
    }

    public static @NotNull ChestInventory createLargeContainer(int windowId, Inventory playerInventory) {
        return new ChestInventory(ROMContainerTypes.LARGE_CHEST, windowId, playerInventory, new SimpleContainer(Chest.LARGE.size), Chest.LARGE);
    }

    public static ChestInventory createLargeContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new ChestInventory(ROMContainerTypes.LARGE_CHEST, windowId, playerInventory, inventory, Chest.LARGE);
    }

    public static @NotNull ChestInventory createCommonContainer(int windowId, Inventory playerInventory) {
        return new ChestInventory(ROMContainerTypes.SMALL_CHEST, windowId, playerInventory, new SimpleContainer(Chest.SMALL.size), Chest.SMALL);
    }

    public static ChestInventory createCommonContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new ChestInventory(ROMContainerTypes.SMALL_CHEST, windowId, playerInventory, inventory, Chest.SMALL);
    }

    protected ChestInventory(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory, Container inventory, Chest chestType) {
        super(menuType, containerId);

        checkContainerSize(inventory, chestType.size);

        this.container = inventory;
        this.chestType = chestType;

        inventory.startOpen(playerInventory.player);

        if (chestType == Chest.SMALL || chestType == Chest.LARGE || chestType == Chest.DAMAGE || chestType == Chest.HEALING || chestType == Chest.EQUIPMENT_BARREL || chestType == Chest.LUNAR || chestType == Chest.RUSTY || chestType == Chest.UTILITY) {
            this.addSlot(new SingleSlot(inventory, 0, 12 + 4 * 18, 8 + 2 * 18));
        } else {
            for (int chestRow = 0; chestRow < chestType.getRowCount(); chestRow++) {
                for (int chestCol = 0; chestCol < chestType.rowLength; chestCol++) {
                    this.addSlot(new Slot(inventory, chestCol + chestRow * chestType.rowLength, 12 + chestCol * 18, 18 + chestRow * 18));
                }
            }
        }

        int leftCol = (chestType.xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, chestType.ySize - (4 - playerInvRow) * 18 - 10));
            }

        }

        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(playerInventory, hotHarSlot, leftCol + hotHarSlot * 18, chestType.ySize - 24));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.chestType.size) {
                if (!this.moveItemStackTo(itemstack1, this.chestType.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.chestType.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        this.container.stopOpen(playerIn);
    }

    public Container getContainer() {
        return this.container;
    }

    @OnlyIn(Dist.CLIENT)
    public Chest getChestType() {
        return this.chestType;
    }
}
