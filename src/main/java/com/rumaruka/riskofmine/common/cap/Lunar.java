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

public class Lunar extends CoffeeCapabilityInstance<Entity> {
    public final CoffeeProperty<Integer> lunar = prop("lunar", 0, IntPropertySerializer.INSTANCE).synced();

    private final Player player;

    public Lunar(Player player) {
        this.player = player;
    }

    @NotNull
    @Override
    public Capability<? extends CoffeeCapabilityInstance<Entity>> getCapability() {
        return ROMCap.LUNAR;
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
    public boolean consumeLunar(Player player, int price) {
        if (!player.isCreative()) {
            if (hasLunar(price)) {
                setLunar(getCurrentLunar() - price);

                return true;
            }

            return false;
        }
        return true;
    }
    public void setLunar(int value) {
        if (getCurrentLunar() != value) {
            lunar.set(value);
        }
    }
    public static int getMaxLunar(Player player) {
        return Integer.MAX_VALUE;
    }
    public void addLunar(int value) {

        setLunar(Math.min(getCurrentLunar() + value, getMaxLunar(player)));
    }
    public void removeLunar(int value) {

        setLunar(Math.min(getCurrentLunar() - value, getMaxLunar(player)));
    }

    public boolean hasLunar(int price) {
        return getCurrentLunar() >= price;
    }
    public int getCurrentLunar() {
        return lunar.get();
    }



    public void detectAndSendChanges() {
        detectAndSendChanges(player.level(), player);
    }

    public void sendAllData() {
        sendAllData(player.level(), player);
    }

    @Nullable
    public static Lunar of(Player player) {
        LazyOptional<Lunar> cap = player.getCapability(ROMCap.LUNAR);
        if (cap.isPresent()) {
            return cap.orElseThrow(IllegalStateException::new);
        }

        return null;
    }
}
