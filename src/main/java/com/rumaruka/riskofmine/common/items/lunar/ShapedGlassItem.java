package com.rumaruka.riskofmine.common.items.lunar;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class ShapedGlassItem extends BaseCollectablesItem {

    public static final UUID healthModifierID = UUID.fromString("208b4d4c-50ef-4b45-a097-4bed633cdbff");

    public ShapedGlassItem() {
        super(Types.LUNAR, Category.DAMAGE, 1);
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

        if (!livingEntity.level().isClientSide()) {
            Player playerEntity = (Player) livingEntity;

            playerEntity.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(slotContext, playerEntity.getUUID(), stack));
            curioShields(playerEntity);
        }
        super.curioTick(slotContext, stack);
    }

    private void curioShields(Player player) {
        Shields shields = Shields.of(player);
        if (shields != null) {
            if (!shields.isHurt() && player.hurtTime == 0) {

                shields.setShields(16);
            }
            shields.detectAndSendChanges();
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        final Multimap<Attribute, AttributeModifier> defaultModifiers;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modificator", 64, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(healthModifierID, "Health Minus", -16, AttributeModifier.Operation.ADDITION));

        defaultModifiers = builder.build();
        return defaultModifiers;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        final Multimap<Attribute, AttributeModifier> defaultModifiers;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        if (slot == EquipmentSlot.OFFHAND) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modificator", 64, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(healthModifierID, "Health Minus", -16, AttributeModifier.Operation.ADDITION));
        }
        defaultModifiers = builder.build();
        return defaultModifiers;
    }

    @Override
    public boolean showAttributesTooltip(String identifier, ItemStack stack) {
        return false;
    }
}
