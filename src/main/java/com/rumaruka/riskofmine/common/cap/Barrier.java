package com.rumaruka.riskofmine.common.cap;

import com.rumaruka.riskofmine.init.ROMCap;
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

public class Barrier extends CoffeeCapabilityInstance<Entity> {
    @NotNull
    public final CoffeeProperty<Integer> barrier = prop("barrier", 0, IntPropertySerializer.INSTANCE).synced();

    private final Player target;

    public Barrier(Player target) {
        this.target = target;
    }

    @NotNull
    @Override
    public Capability<? extends CoffeeCapabilityInstance<Entity>> getCapability() {
        return ROMCap.BARRIER;
    }

    @NotNull
    @Override
    public CapabilityOwnerCodec<Entity> getOwnerSerializer() {
        return CapabilityOwner.ENTITY.getSerializer();
    }

    @Override
    public void sendChangesToClients(@NotNull SimpleChannel channel, @NotNull Object data) {


            channel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> target), data);

    }
    public boolean isHurt() {
        return this.getCurrentBarrier() > 0 && this.getCurrentBarrier() < 16;
    }

    public boolean consumeBarrier(Player player, int price) {
        if (!player.isCreative()) {
            if (hasBarrier()) {
                setBarrier(getCurrentBarrier() - price);

                return true;
            }

            return false;
        }
        return true;
    }

    public void setBarrier(int value) {
        if (getCurrentBarrier() != value) {
            barrier.set(value);
        }
    }

    public  int getMaxBarrier(Player player) {
        return Integer.MAX_VALUE;
    }

    public void addBarrier(int value) {

        setBarrier(Math.min(getCurrentBarrier() + value, getMaxBarrier(target)));
    }


    public void removeBarrier(int value) {

        setBarrier(Math.max(getCurrentBarrier() - value, 0));
    }

    public boolean hasBarrier() {
        return getCurrentBarrier() > 0;
    }

    public int getCurrentBarrier() {

        return barrier.get();
    }
    public void detectAndSendChanges() {
        detectAndSendChanges(target.level(), target);
    }

    public void sendAllData() {
        sendAllData(target.level(), target);
    }

    @Nullable
    public static Barrier of(Player player) {
        LazyOptional<Barrier> cap = player.getCapability(ROMCap.BARRIER);
        if (cap.isPresent()) {
            return cap.orElseThrow(IllegalStateException::new);
        }

        return null;
    }
}
