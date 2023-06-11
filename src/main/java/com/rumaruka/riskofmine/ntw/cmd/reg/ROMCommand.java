package com.rumaruka.riskofmine.ntw.cmd.reg;

import com.mojang.brigadier.CommandDispatcher;
import com.rumaruka.riskofmine.ntw.cmd.LunarAddCommand;
import com.rumaruka.riskofmine.ntw.cmd.LunarSetCommand;
import com.rumaruka.riskofmine.ntw.cmd.MoneyAddCommand;
import com.rumaruka.riskofmine.ntw.cmd.MoneySetCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ROMCommand {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        MoneyAddCommand.register(dispatcher);
        MoneySetCommand.register(dispatcher);
        LunarAddCommand.register(dispatcher);
        LunarSetCommand.register(dispatcher);


    }
}
