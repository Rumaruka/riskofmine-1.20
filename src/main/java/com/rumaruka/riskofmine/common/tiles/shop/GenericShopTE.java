package com.rumaruka.riskofmine.common.tiles.shop;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.blocks.chest.GenericChestBlock;
import com.rumaruka.riskofmine.common.inventory.ChestShopInventory;
import com.rumaruka.riskofmine.init.ROMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class GenericShopTE extends RandomizableContainerBlockEntity implements LidBlockEntity {

    private NonNullList<ItemStack> items;

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level level, BlockPos pos, BlockState blockState) {
            GenericShopTE.playSound(level, pos, blockState, ROMSounds.ROM_CHEST_OPEN.get());
        }

        protected void onClose(Level level, BlockPos pos, BlockState blockState) {
            GenericShopTE.playSound(level, pos, blockState, ROMSounds.ROM_CHEST_OPEN.get());
        }

        protected void openerCountChanged(Level level, BlockPos pos, BlockState blockState, int previousCount, int newCount) {
            GenericShopTE.this.signalOpenCount(level, pos, blockState, previousCount, newCount);
        }

        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof ChestShopInventory)) {
                return false;
            } else {
                Container container = ((ChestShopInventory) player.containerMenu).getContainer();
                return container instanceof GenericShopTE || container instanceof CompoundContainer && ((CompoundContainer) container).contains(GenericShopTE.this);
            }
        }
    };

    private final ChestLidController chestLidController = new ChestLidController();

    private final Chest chestType;
    private final Block blockToUse;

    protected GenericShopTE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, Chest chestTypeIn, Block blockToUseIn) {
        super(blockEntityType, blockPos, blockState);

        this.items = NonNullList.<ItemStack>withSize(chestTypeIn.size, ItemStack.EMPTY);
        this.chestType = chestTypeIn;
        this.blockToUse = blockToUseIn;
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(RiskOfMine.MODID + ".container." + this.chestType.getId() + "_shop");
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }
    }

    public static void lidAnimateTick(Level level, BlockPos blockPos, BlockState blockState, GenericShopTE chestBlockEntity) {
        chestBlockEntity.chestLidController.tickLid();
    }

    static void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        double d0 = (double) blockPos.getX() + 0.5D;
        double d1 = (double) blockPos.getY() + 0.5D;
        double d2 = (double) blockPos.getZ() + 0.5D;

        level.playSound((Player) null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = NonNullList.<ItemStack>withSize(this.getChestType().size, ItemStack.EMPTY);

        for (int i = 0; i < itemsIn.size(); i++) {
            if (i < this.items.size()) {
                this.getItems().set(i, itemsIn.get(i));
            }
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockstate = blockGetter.getBlockState(blockPos);

        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = blockGetter.getBlockEntity(blockPos);

            if (blockentity instanceof GenericShopTE) {
                return ((GenericShopTE) blockentity).openersCounter.getOpenerCount();
            }
        }

        return 0;
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, newCount);
    }

    public void wasPlaced(@Nullable LivingEntity livingEntity, ItemStack stack) {
    }

    public void removeAdornments() {
    }

    public Chest getChestType() {
        Chest type = Chest.SMALL;

        if (this.hasLevel()) {
            Chest typeFromBlock = GenericChestBlock.getTypeFromBlock(this.getBlockState().getBlock());

            if (typeFromBlock != null) {
                type = typeFromBlock;
            }
        }

        return type;
    }

    public Block getBlockToUse() {
        return this.blockToUse;
    }
}
