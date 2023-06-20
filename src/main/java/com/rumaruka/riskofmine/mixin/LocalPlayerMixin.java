package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.DoubleJumpPacket;
import com.rumaruka.riskofmine.utils.ROMDoubleEffect;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    private int jumpCount = 0;
    private boolean jumpedLastTick = false;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo info) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.HOPOO_FEATHER))) {
            if (player.onGround() || player.onClimbable()) {
                jumpCount = ROMUtils.counting(player, new ItemStack(ROMItems.HOPOO_FEATHER));

            } else if (!jumpedLastTick && jumpCount > 0 && player.getDeltaMovement().y < 0) {
                if (player.input.jumping && !player.getAbilities().flying) {
                    if (canJump(player)) {
                        --jumpCount;
                        player.jumpFromGround();
                        ROMDoubleEffect.play(player);
                        ROMNetwork.getInstance().sendToServer(new DoubleJumpPacket());


                    }
                }
            }
            jumpedLastTick = player.input.jumping;
        }
        if (ROMUtils.checkCurios(player, new ItemStack(ROMItems.HOPOO_FEATHER))) {
            if (player.onGround() || player.onClimbable()) {
                jumpCount = ROMUtils.countingCurio(player, ROMItems.HOPOO_FEATHER);

            } else if (!jumpedLastTick && jumpCount > 0 && player.getDeltaMovement().y < 0) {
                if (player.input.jumping && !player.getAbilities().flying) {
                    if (canJump(player)) {
                        --jumpCount;
                        player.jumpFromGround();
                        ROMDoubleEffect.play(player);
                        ROMNetwork.getInstance().sendToServer(new DoubleJumpPacket());


                    }
                }
            }
            jumpedLastTick = player.input.jumping;
        }
    }


    private boolean wearingUsableElytra(LocalPlayer player) {
        ItemStack chestItemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        return chestItemStack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(chestItemStack);
    }

    private boolean canJump(LocalPlayer player) {
        return !wearingUsableElytra(player) && !player.isFallFlying() && !player.isVehicle()
                && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION);
    }


}




