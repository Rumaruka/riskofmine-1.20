package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(Entity.class)
public abstract class EntityMixin implements IOverloading {
    @Shadow
    public abstract int getId();

    @Shadow
    public Level level;
    private boolean isOverloading;

    @Override
    public boolean isOverloading() {

        return isOverloading;
    }

    @Override
    public void setOverloading(boolean isOver) {
        if (isOver != isOverloading && !level.isClientSide()) {
            ROMNetwork.network.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (Entity) (Object) this), new OverloadingPacket(this.getId(), isOver));

        }
        isOverloading = isOver;
    }

}
