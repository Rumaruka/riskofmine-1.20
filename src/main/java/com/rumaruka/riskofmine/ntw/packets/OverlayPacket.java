package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OverlayPacket {


    public OverlayPacket(FriendlyByteBuf byteBuf) {

    }

    public OverlayPacket() {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Money money = Money.of(ROMUtils.getPlayer());
            Lunar lunar = Lunar.of(ROMUtils.getPlayer());
            if (money != null) {
                money.detectAndSendChanges();

            }
            if (lunar != null) {
                lunar.detectAndSendChanges();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
