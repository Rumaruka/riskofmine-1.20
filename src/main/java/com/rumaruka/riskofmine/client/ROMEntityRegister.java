package com.rumaruka.riskofmine.client;

import com.rumaruka.riskofmine.client.render.HealthOrbRenderer;
import com.rumaruka.riskofmine.client.render.StickyBombRenderer;
import com.rumaruka.riskofmine.init.ROMEntity;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ROMEntityRegister {

    public static void renderEntity() {
        EntityRenderers.register(ROMEntity.HEALTH_ORB, HealthOrbRenderer::new);
        EntityRenderers.register(ROMEntity.STICKY_BOMB, StickyBombRenderer::new);

    }
}
