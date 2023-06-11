package com.rumaruka.riskofmine.client.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.common.tiles.chest.SmallChestTE;
import com.rumaruka.riskofmine.common.tiles.shop.MultiShopTE;
import com.rumaruka.riskofmine.init.ROMModels;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class MultiShopTESR implements BlockEntityRenderer<MultiShopTE> {
    private final TimeModel model;

    public MultiShopTESR( BlockEntityRendererProvider.Context rendererDispatcherIn) {
        model = new TimeModel(ModelConfiguration.builder(ROMModels.MULTI_SHOP_OPEN).build());
    }

    @Override
    public void render(MultiShopTE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ResourceLocation texture = getMultiShopOpenTexture(pBlockEntity);
        this.model.renderType(texture);
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5);

        pPoseStack.scale(1.25f, 2f, 1.25f);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(model.renderType(texture)), pPackedLight, pPackedOverlay, 1, 1, 1, 1);

        pPoseStack.popPose();
    }

    private ResourceLocation getMultiShopOpenTexture(MultiShopTE tileEntityIn) {
        return rl("textures/tile/multi_shop_open.png");
    }


}
