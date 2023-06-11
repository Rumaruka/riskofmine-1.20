package com.rumaruka.riskofmine.client.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.common.tiles.chest.SmallChestTE;
import com.rumaruka.riskofmine.init.ROMModels;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.api.client.render.tile.ProfiledTileEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

import java.util.function.Function;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class SmallChestTESR implements  BlockEntityRenderer<SmallChestTE> {


    private final TimeModel model;

    public SmallChestTESR(BlockEntityRendererProvider.Context rendererDispatcherIn) {

        model = new TimeModel(ModelConfiguration.builder(ROMModels.SMALL_CHEST).build());
    }

    private ResourceLocation getTexture(SmallChestTE tileEntityIn) {
        return rl("textures/tile/small_chest.png");
    }

    @Override
    public void render(SmallChestTE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ResourceLocation texture = getTexture(pBlockEntity);
       this.model.renderType(texture);
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5F);
        pPoseStack.scale(1.15f, 1f, 1.25f);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(model.renderType(texture)), pPackedLight, pPackedOverlay, 1, 1, 1, 1);

        pPoseStack.popPose();
    }


}
