package com.rumaruka.riskofmine.ntw;

import com.rumaruka.riskofmine.ntw.helper.ISimplePacket;
import com.rumaruka.riskofmine.ntw.packets.DoubleJumpPacket;
import com.rumaruka.riskofmine.ntw.packets.ItemActivationPacket;
import com.rumaruka.riskofmine.ntw.packets.OverlayPacket;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.rumaruka.riskofmine.RiskOfMine.rl;
import static ru.timeconqueror.timecore.api.util.Hacks.promise;

public class ROMNetwork {
    private static ROMNetwork instance = promise();
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel network = NetworkRegistry.ChannelBuilder.named(rl("network"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    private static int id = 0;


    public ROMNetwork() {

    }

    public static int nextID() {
        return id++;
    }

    public static ROMNetwork getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Attempt to call network getInstance before network is setup");
        }

        return instance;
    }

    public static void setup() {
        if (instance != null) {
            return;
        }

        instance = new ROMNetwork();
        instance.registerPacket(InventoryTopStacksSyncPacket.class, InventoryTopStacksSyncPacket::new, NetworkDirection.PLAY_TO_CLIENT);
        network.registerMessage(nextID(), OverlayPacket.class, OverlayPacket::toBytes, OverlayPacket::new, OverlayPacket::handle);
        network.messageBuilder(ItemActivationPacket.class, nextID())
                .encoder(ItemActivationPacket::toBytes)
                .decoder(ItemActivationPacket::new)
                .consumerMainThread(ItemActivationPacket::handle)
                .add();
        network.registerMessage(nextID(), DoubleJumpPacket.class, DoubleJumpPacket::toBytes, DoubleJumpPacket::new, DoubleJumpPacket::handle);
        network.registerMessage(nextID(), OverloadingPacket.class, OverloadingPacket::toBytes, OverloadingPacket::new, OverloadingPacket::handle);

    }

    /**
     * Registers a new {@link ISimplePacket}
     *
     * @param clazz   Packet class
     * @param decoder Packet decoder, typically the constructor
     * @param <MSG>   Packet class type
     */
    public <MSG extends ISimplePacket> void registerPacket(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> decoder, @Nullable NetworkDirection direction) {
        registerPacket(clazz, ISimplePacket::encode, decoder, ISimplePacket::handle, direction);

    }

    /**
     * Registers a new generic packet
     *
     * @param clazz     Packet class
     * @param encoder   Encodes a packet to the buffer
     * @param decoder   Packet decoder, typically the constructor
     * @param consumer  Logic to handle a packet
     * @param direction Network direction for validation. Pass null for no direction
     * @param <MSG>     Packet class type
     */
    public <MSG> void registerPacket(Class<MSG> clazz, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer, @Nullable NetworkDirection direction) {
        network.registerMessage(id++, clazz, encoder, decoder, consumer, Optional.ofNullable(direction));
    }

    /* Sending packets */

    /**
     * Sends a packet to the server
     *
     * @param msg Packet to send
     */
    public void sendToServer(Object msg) {
        network.sendToServer(msg);
    }

    /**
     * Sends a packet to the given packet distributor
     *
     * @param target  Packet target
     * @param message Packet to send
     */
    public void send(PacketDistributor.PacketTarget target, Object message) {
        network.send(target, message);
    }

    /**
     * Sends a vanilla packet to the given entity
     *
     * @param player Player receiving the packet
     * @param packet Packet
     */
    public void sendVanillaPacket(Packet<?> packet, Entity player) {
        if (player instanceof ServerPlayer) {
            ((ServerPlayer) player).connection.send(packet);
        }
    }

    /**
     * Sends a packet to a player
     *
     * @param msg    Packet
     * @param player Player to send
     */
    public void sendTo(Object msg, Player player) {
        if (player instanceof ServerPlayer) {
            sendTo(msg, (ServerPlayer) player);
        }
    }

    /**
     * Sends a packet to a player
     *
     * @param msg    Packet
     * @param player Player to send
     */
    public void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            network.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    /**
     * Sends a packet to players near a location
     *
     * @param msg         Packet to send
     * @param serverWorld World instance
     * @param position    Position within range
     */
    public void sendToClientsAround(Object msg, ServerLevel serverWorld, BlockPos position) {
        LevelChunk chunk = serverWorld.getChunkAt(position);
        network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
    }

    /**
     * Sends a packet to all entities tracking the given entity
     *
     * @param msg    Packet
     * @param entity Entity to check
     */
    public void sendToTrackingAndSelf(Object msg, Entity entity) {
        network.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }


    public void sendToTracking(Object msg, Entity entity) {
        network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

    public void sendToClientsAround(Object msg, @Nullable LevelAccessor world, BlockPos position) {
        if (world instanceof ServerLevel) {
            sendToClientsAround(msg, (ServerLevel) world, position);
        }
    }


}
