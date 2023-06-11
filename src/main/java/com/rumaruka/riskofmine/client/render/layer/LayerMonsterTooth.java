package com.rumaruka.riskofmine.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class LayerMonsterTooth extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public LayerMonsterTooth(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer playerEntity, float pLimbSwing, float pLimbSwingAmount, float pTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        for (int i = 0; i < playerEntity.getInventory().getContainerSize(); i++) {
            ItemStack itemStack = playerEntity.getInventory().getItem(i);
            if (itemStack.getItem() == ROMItems.MONSTER_TOOTH) {
                renderMonsterTooth(itemStack, matrixStack, pBuffer, pPackedLight, playerEntity, pTicks);

            }
        }
        //FIXME
//        if (CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ROMItems.MONSTER_TOOTH).isPresent()) {
//            ItemStack curioStack = CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ROMItems.MONSTER_TOOTH).get().stack();
//            renderMonsterTooth(curioStack, matrixStack, pBuffer, pPackedLight, playerEntity, pTicks);
//        }
    }

    private void renderMonsterTooth(ItemStack itemStack, PoseStack matrixStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer playerEntity, float pTicks) {


        matrixStack.pushPose();
        matrixStack.translate(0.0F, 0.0F, 0.125F);
        double d0 = Mth.lerp(pTicks, playerEntity.xCloakO, playerEntity.xCloak) - Mth.lerp((double) pTicks, playerEntity.xo, playerEntity.getX());
        double d1 = Mth.lerp(pTicks, playerEntity.yCloakO, playerEntity.yCloak) - Mth.lerp((double) pTicks, playerEntity.yo, playerEntity.getY());
        double d2 = Mth.lerp(pTicks, playerEntity.zCloakO, playerEntity.zCloak) - Mth.lerp((double) pTicks, playerEntity.zo, playerEntity.getZ());
        float f = playerEntity.yBodyRotO + (playerEntity.yBodyRot - playerEntity.yBodyRotO);
        double d3 = Mth.sin(f * ((float) Math.PI / 180F));
        double d4 = -Mth.cos(f * ((float) Math.PI / 180F));
        float f1 = (float) d1 * 10.0F;
        f1 = Mth.clamp(f1, -.0F, 32.0F);
        float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
        f2 = Mth.clamp(f2, 0.0F, 150.0F);
        float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
        f3 = Mth.clamp(f3, 20.0F, 20.0F);
        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        float f4 = Mth.lerp(pTicks, playerEntity.oBob, playerEntity.bob);
        f1 = f1 + Mth.sin(Mth.lerp(pTicks, playerEntity.walkDistO, playerEntity.walkDist) * 6.0F) * 32.0F * f4;
        if (playerEntity.isCrouching()) {
            f1 += 25.0F;
        }

        matrixStack.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
        Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, pPackedLight, OverlayTexture.NO_OVERLAY, matrixStack, pBuffer, Minecraft.getInstance().level,0);

        matrixStack.popPose();


    }
}
