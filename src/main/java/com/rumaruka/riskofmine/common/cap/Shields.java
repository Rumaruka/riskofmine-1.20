package com.rumaruka.riskofmine.common.cap;

import com.rumaruka.riskofmine.init.ROMCap;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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

public class Shields extends CoffeeCapabilityInstance<Entity> {
    @NotNull
    public final CoffeeProperty<Integer> shields = prop("shields", 0, IntPropertySerializer.INSTANCE).synced();

    private final LivingEntity target;

    public Shields(LivingEntity target) {
        this.target = target;
    }

    @NotNull
    @Override
    public Capability<? extends CoffeeCapabilityInstance<Entity>> getCapability() {
        return ROMCap.SHIELDS;
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
        return this.getCurrentShields() > 0 && this.getCurrentShields() < 16;
    }

    public boolean consumeShields(Player player, int price) {
        if (!player.isCreative()) {
            if (hasShields()) {
                setShields(getCurrentShields() - price);

                return true;
            }

            return false;
        }
        return true;
    }

    public void setShields(int value) {
        if (getCurrentShields() != value) {
            shields.set(value);
        }
    }

    public  int getMaxShields(LivingEntity player) {
        return Integer.MAX_VALUE;
    }

    public void addShields(int value) {

        setShields(Math.min(getCurrentShields() + value, getMaxShields(target)));
    }


    public void removeShields(int value) {

        setShields(Math.max(getCurrentShields() - value, 0));
    }

    public boolean hasShields() {
        return getCurrentShields() > 0;
    }

    public int getCurrentShields() {

        return shields.get();
    }
    public void detectAndSendChanges() {
        detectAndSendChanges(target.level(), target);
    }

    public void sendAllData() {
        sendAllData(target.level(), target);
    }

    @Nullable
    public static Shields of(LivingEntity player) {
        LazyOptional<Shields> cap = player.getCapability(ROMCap.SHIELDS);
        if (cap.isPresent()) {
            return cap.orElseThrow(IllegalStateException::new);
        }

        return null;
    }
}
