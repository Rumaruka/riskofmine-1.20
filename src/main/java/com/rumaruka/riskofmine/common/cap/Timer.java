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

public class Timer extends CoffeeCapabilityInstance<Entity> {
    public final CoffeeProperty<Integer> time = prop("time",0, IntPropertySerializer.INSTANCE).synced();
    private final Player player;

    public Timer(Player player) {
        this.player = player;
    }

    @NotNull
    @Override
    public Capability<? extends CoffeeCapabilityInstance<Entity>> getCapability() {
        return ROMCap.TIMER;
    }

    @NotNull
    @Override
    public CapabilityOwnerCodec<Entity> getOwnerSerializer() {
        return CapabilityOwner.ENTITY.getSerializer();
    }





    public boolean consumeTime(Player player, int time) {
        if (!player.isCreative()) {
            if (hasTime(time)) {
                setTime(getCurrentTime() - time);

                return true;
            }

            return false;
        }
        return true;
    }
    public void setTime(int value) {
        if (getCurrentTime() != value) {
            time.set(value);
        }
    }
    public static int getMaxTime(Player player) {
        return Integer.MAX_VALUE;
    }
    public void addTime(int value) {

        setTime(Math.min(getCurrentTime() + value, getMaxTime(player)));
    }
    public void removeTime(int value) {

        setTime(Math.min(getCurrentTime() - value, getMaxTime(player)));
    }

    public boolean hasTime(int time) {
        return getCurrentTime() >= time;
    }
    public int getCurrentTime() {
        return time.get();
    }
    
    
    
    @Override
    public void sendChangesToClients(@NotNull SimpleChannel channel, @NotNull Object data) {
        if (player instanceof ServerPlayer serverPlayer) {
            channel.send(PacketDistributor.PLAYER.with(() -> serverPlayer), data);
        }
    }


    public void detectAndSendChanges() {
        detectAndSendChanges(player.level(), player);
    }

    public void sendAllData() {
        sendAllData(player.level(), player);
    }
    @Nullable
    public static Timer of(Player player) {
        LazyOptional<Timer> cap = player.getCapability(ROMCap.TIMER);
        if (cap.isPresent()) {
            return cap.orElseThrow(IllegalStateException::new);
        }

        return null;
    }
}
