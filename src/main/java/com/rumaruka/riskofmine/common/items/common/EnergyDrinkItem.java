package com.rumaruka.riskofmine.common.items.common;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.utils.ROMMathFormula;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;


public class EnergyDrinkItem extends BaseCollectablesItem {
    private static final UUID SPEED_MODIFIER_SPRINTING_UUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");

    public EnergyDrinkItem() {
        super(Types.COMMON, Category.UTILITY, 64);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide()) {
            Player playerEntity = (Player) pEntity;
            if (playerEntity.getOffhandItem().getItem() == this) {
                playerEntity.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(EquipmentSlot.OFFHAND, pStack));

            }

        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.level.isClientSide()) {
            Player playerEntity = (Player) livingEntity;
            playerEntity.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(slotContext, playerEntity.getUUID(), stack));
        }
        super.curioTick(slotContext, stack);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        final Multimap<Attribute, AttributeModifier> defaultModifiers;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        if (slot == EquipmentSlot.OFFHAND) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPEED_MODIFIER_SPRINTING_UUID, "Speed", ROMMathFormula.speedIncreasing(stack.getCount()), AttributeModifier.Operation.ADDITION));
        }

        defaultModifiers = builder.build();
        return defaultModifiers;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        final Multimap<Attribute, AttributeModifier> defaultModifiers;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPEED_MODIFIER_SPRINTING_UUID, "Speed", ROMMathFormula.speedIncreasing(stack.getCount()), AttributeModifier.Operation.ADDITION));


        defaultModifiers = builder.build();
        return defaultModifiers;
    }

    @Override
    public boolean showAttributesTooltip(String identifier, ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.translatable((getColor() + getTypeName())));
        tooltip.add(Component.translatable("ror.shiftpress.info"));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("ror.energydrink.info"));
            tooltip.add(Component.translatable("[Stacks:" + pStack.getCount() + "]"));
        }
    }
}
