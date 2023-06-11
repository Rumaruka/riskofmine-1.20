package com.rumaruka.riskofmine.ntw.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.rumaruka.riskofmine.common.cap.Lunar;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

public class LunarSetCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> literalCommandNode = dispatcher.register(Commands.literal("lunar").requires((p_198442_0_) -> {
            return p_198442_0_.hasPermission(2);
        }).then(Commands.literal("set").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((p_198445_0_) -> {
            return setLunar(p_198445_0_.getSource(), EntityArgument.getPlayers(p_198445_0_, "targets"), IntegerArgumentType.getInteger(p_198445_0_, "amount"));
        })))));
        dispatcher.register(Commands.literal("rom_lunar").requires((p_198441_0_) -> {
            return p_198441_0_.hasPermission(2);
        }).redirect(literalCommandNode));
    }

    private static int setLunar(CommandSourceStack source, Collection<? extends ServerPlayer> playerEntities, int amount) {
        for (ServerPlayer player : playerEntities) {
            Lunar lunar = Lunar.of(player);
            if (lunar != null) {

                lunar.setLunar(amount);
                lunar.detectAndSendChanges();

            }


        }
        if (playerEntities.size() == 1) {
            source.sendSuccess(()->Component.translatable("commands.set." + "lunar" + ".success.single", amount, playerEntities.iterator().next().getDisplayName()), true);

        }
        return playerEntities.size();
    }

}
