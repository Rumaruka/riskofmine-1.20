package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "shouldEntityAppearGlowing", at = @At("RETURN"), cancellable = true)
    private void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> ci) {


        if (ROMUtils.hasOverloadingOnClient(entity)) {
            ci.setReturnValue(true);
        }


    }
}

