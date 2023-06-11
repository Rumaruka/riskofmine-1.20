package com.rumaruka.riskofmine.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {


    @Inject(method = "renderEntity", at = @At("HEAD"))
    private void renderEntity(Entity pEntity, double pCamX, double pCamY, double pCamZ, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, CallbackInfo ci) {

        if (ROMUtils.hasOverloadingOnClient(pEntity)) {
            Minecraft.getInstance().renderBuffers().outlineBufferSource().setColor(0, 0, 255, 255);
        }


    }


}



