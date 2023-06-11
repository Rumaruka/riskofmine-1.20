package com.rumaruka.riskofmine.common.cap;

import com.rumaruka.riskofmine.init.ROMCap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.common.capability.CoffeeCapabilityInstance;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;
import ru.timeconqueror.timecore.common.capability.owner.serializer.CapabilityOwnerCodec;
import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.serializer.IntPropertySerializer;

public class Money extends CoffeeCapabilityInstance<Entity> {
    public final CoffeeProperty<Integer> money = prop("money", 0, IntPropertySerializer.INSTANCE).synced();

    private final Player player;

    public Money(Player player) {
        this.player = player;
    }

    @NotNull
    @Override
    public Capability<? extends CoffeeCapabilityInstance<Entity>> getCapability() {
        return ROMCap.MONEY;
    }




    @NotNull
    @Override
    public CapabilityOwnerCodec<Entity> getOwnerSerializer() {
        return CapabilityOwner.ENTITY.getSerializer();
    }

    @Override
    public void sendChangesToClients(@NotNull SimpleChannel channel, @NotNull Object data) {

        if (player instanceof ServerPlayer serverPlayer) {
            channel.send(PacketDistributor.PLAYER.with(() -> serverPlayer), data);
        }
    }
    public boolean consumeMoney(Player player, int price) {
        if (!player.isCreative()) {
            if (hasMoney(price)) {
                setMoney(getCurrentMoney() - price);

                return true;
            }

            return false;
        }
        return true;
    }
    public void setMoney(int value) {
        if (getCurrentMoney() != value) {
            money.set(value);
        }
    }
    public static int getMaxMoney(Player player) {
        return Integer.MAX_VALUE;
    }
    public void addMoney(int value) {

        setMoney(Math.min(getCurrentMoney() + value, getMaxMoney(player)));
    }
    public void removeMoney(int value) {

        setMoney(Math.min(getCurrentMoney() - value, getMaxMoney(player)));
    }

    public boolean hasMoney(int price) {
        return getCurrentMoney() >= price;
    }
    public int getCurrentMoney() {
        return money.get();
    }

    public void detectAndSendChanges() {
        detectAndSendChanges(player.level(), player);
    }

    public void sendAllData() {
        sendAllData(player.level(), player);
    }

    @Nullable
    public static Money of(Player player) {
        LazyOptional<Money> cap = player.getCapability(ROMCap.MONEY);
        if (cap.isPresent()) {
            return cap.orElseThrow(IllegalStateException::new);
        }

        return null;
    }
}
