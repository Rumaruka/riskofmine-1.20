package com.rumaruka.riskofmine.common.items.eqiupment;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.rumaruka.riskofmine.utils.ROMUtils.removeNegativeEffect;

public class BlastShowerItem extends EquipmentBase {
    public BlastShowerItem() {
        super(Category.UTILITY);
        //cooldownMinus= ROMItems.ALIEN_HEAD.cooldownMinus;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            pPlayer.curePotionEffects(stack);

            if (!pPlayer.getAbilities().instabuild) {

                for (int i = 0; i < pPlayer.getInventory().getContainerSize(); i++) {
                    ItemStack itemStack = pPlayer.getInventory().getItem(i);
                    if (itemStack.getItem() == ROMItems.ALIEN_HEAD) {
                        pPlayer.removeAllEffects();
                        removeNegativeEffect(pPlayer);
                        pPlayer.getCooldowns().addCooldown(this, ROMConfig
                                .GENERAL.cooldownEq.get() - cooldownMinus);
                        MinecraftForge.EVENT_BUS.register(new ProjectileRemoveEvent());
                    }

                }
                pPlayer.removeAllEffects();
                removeNegativeEffect(pPlayer);
                MinecraftForge.EVENT_BUS.register(new ProjectileRemoveEvent());
                pPlayer.getCooldowns().addCooldown(this, ROMConfig.GENERAL.cooldownEq.get());

            }
        }

        MinecraftForge.EVENT_BUS.unregister(new ProjectileRemoveEvent());
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.translatable("ror.alt.info"));
        if (Screen.hasAltDown()) {

            tooltip.add(Component.translatable("riskofmine.rarity" + ":"));
            tooltip.add(Component.translatable((getColor() + getTypeName())));
            tooltip.add(Component.translatable("riskofmine.category" + ":"));
            tooltip.add(Component.translatable((getColors() + getCategoryName())));
        }
        tooltip.add(Component.translatable("ror.shiftpress.info"));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("ror.blast_shower.info"));
        }
    }


    @Mod.EventBusSubscriber(modid = RiskOfMine.MODID)
    public static class ProjectileRemoveEvent {
        @SubscribeEvent
        public void onManipulationProjectiles(ProjectileImpactEvent event) {
            Player playerEntity = Minecraft.getInstance().player;
            Projectile projectileEntity = (Projectile) event.getEntity();

            if (playerEntity != null && playerEntity.level().isClientSide) {
                projectileEntity.kill();
            }
        }
    }
}
