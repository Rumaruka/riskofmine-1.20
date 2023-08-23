package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record OverloadingPacket(int entityId, boolean overloading) {


    public OverloadingPacket(FriendlyByteBuf byteBuf) {
        this(byteBuf.readInt(), byteBuf.readBoolean());

    }


    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(overloading);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityId);

            if (entity instanceof IOverloading over) {
                over.setOverloading(overloading);
            }


        });
        ctx.get().setPacketHandled(true);
    }
}
