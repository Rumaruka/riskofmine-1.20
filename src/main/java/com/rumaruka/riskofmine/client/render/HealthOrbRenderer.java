package com.rumaruka.riskofmine.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HealthOrbRenderer extends EntityRenderer<HealthOrbEntity> {
    private static final ResourceLocation HEAL_ORB_TEXTURES = new ResourceLocation("riskofmine:textures/entity/health_orb.png");
    private static final RenderType RENDER_TYPE = RenderType.entityTranslucentCull(HEAL_ORB_TEXTURES);
    public HealthOrbRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;

    }

    @Override
    public void render(HealthOrbEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        int i = pEntity.getIcon();
        float f = (float) (i % 3 * 16) / 64.0F;
        float f1 = (float) (i % 3 * 16 + 16) / 64.0F;
        float f2 = (float) (i / 3 * 16) / 64.0F;
        float f3 = (float) (i / 3 * 16 + 16) / 64.0F;
        float f8 = ((float) pEntity.tickCount + pPartialTicks) / 2.0F;
        int j = (int) ((Mth.sin(f8 + 0.0F) + 1.0F) * 0.5F * 255.0F);
        int l = (int) ((Mth.sin(f8 + 4.1887903F) + 1.0F) * 0.1F * 255.0F);
        pMatrixStack.translate(0.0D, 0.1F, 0.0D);
        pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        float f9 = 0.3F;
        pMatrixStack.scale(0.3F, 0.3F, 0.3F);
        VertexConsumer  ivertexbuilder = pBuffer.getBuffer(RENDER_TYPE);
        PoseStack.Pose  matrixstack$entry = pMatrixStack.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        vertex(ivertexbuilder, matrix4f, matrix3f, -0.5F, -0.25F, 255, j, l, f, f3, pPackedLight);
        vertex(ivertexbuilder, matrix4f, matrix3f, 0.5F, -0.25F, 255, j, l, f1, f3, pPackedLight);
        vertex(ivertexbuilder, matrix4f, matrix3f, 0.5F, 0.75F, 255, j, l, f1, f2, pPackedLight);
        vertex(ivertexbuilder, matrix4f, matrix3f, -0.5F, 0.75F, 255, j, l, f, f2, pPackedLight);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
    private static void vertex(VertexConsumer pConsumer, Matrix4f pMatrix, Matrix3f pMatrixNormal, float pX, float pY, int pRed, int pGreen, int pBlue, float pTexU, float pTexV, int pPackedLight) {
        pConsumer.vertex(pMatrix, pX, pY, 0.0F).color(pRed, pGreen, pBlue, 128).uv(pTexU, pTexV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(pMatrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }
    protected int getBlockLightLevel(HealthOrbEntity pEntity, BlockPos pPos) {
        return Mth.clamp(super.getBlockLightLevel(pEntity, pPos) + 7, 0, 15);
    }

    @Override
    public ResourceLocation getTextureLocation(HealthOrbEntity pEntity) {
        return HEAL_ORB_TEXTURES;
    }
}
