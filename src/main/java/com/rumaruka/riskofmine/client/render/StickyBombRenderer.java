package com.rumaruka.riskofmine.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.init.ROMModels;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.rumaruka.riskofmine.RiskOfMine.rl;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class StickyBombRenderer extends EntityRenderer<StickyBombEntity> {

    private final TimeModel stickyBombModel;
    public StickyBombRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        stickyBombModel = new TimeModel(ModelConfiguration.builder(ROMModels.STICKY_BOMB).build());
    }

    @Override
    public void render(StickyBombEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        ResourceLocation texture = getTextureLocation(pEntity);
        this.stickyBombModel.renderType(texture);
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5F);
        pPoseStack.scale(1.15f, 1f, 1.25f);
        stickyBombModel.renderToBuffer(pPoseStack, pBuffer.getBuffer(stickyBombModel.renderType(texture)), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(StickyBombEntity pEntity) {
        return rl("textures/item/sticky_bomb_model.png");
    }
}
