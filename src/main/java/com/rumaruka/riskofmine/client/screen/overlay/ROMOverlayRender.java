package com.rumaruka.riskofmine.client.screen.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverlayPacket;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import ru.timeconqueror.timecore.api.util.client.DrawHelper;

import java.awt.*;

@Mod.EventBusSubscriber(modid = RiskOfMine.MODID)
public class ROMOverlayRender {

    public static KeyMapping KEY_SHOW_OVERLAYS = new KeyMapping("key.mapping.show_overlay.name", GLFW.GLFW_KEY_M, "key.mapping.category.name");

    private static final Minecraft mc = ROMUtils.getMc();

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent e) {
        e.register(KEY_SHOW_OVERLAYS);
    }

    @SubscribeEvent
    public static void inputEvent(InputEvent.Key event) {
        if (mc.screen != null) return;
        if (KEY_SHOW_OVERLAYS.isDown() && event.getAction() == GLFW.GLFW_PRESS) {
            ROMNetwork.getInstance().sendToServer(new OverlayPacket());
        }
    }

    @SubscribeEvent
    public static void renderOverlay(CustomizeGuiOverlayEvent.Chat event) {
        if (KEY_SHOW_OVERLAYS.isDown()) {
            renderNearbyMoneyDisplay(event.getGuiGraphics());
            renderNearbyLunarDisplay(event.getGuiGraphics());
        } else {
            renderNearbyShieldsDisplay(event.getGuiGraphics());
            renderNearbyBarrierDisplay(event.getGuiGraphics());

        }

    }

    private static void renderNearbyMoneyDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Money money = Money.of(player);
            if (money != null) {
                String toDisplay = getMoneyDisplay(money);
                Color color = Color.magenta;
                DrawHelper.drawString(stack, font, toDisplay, 27.5f, 20, color.getRGB());
            }
        }
    }
    private static void renderNearbyBarrierDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Barrier barrier = Barrier.of(player);
            if (barrier != null) {
                String toDisplay = getBarrierDisplay(barrier);
                Color color = Color.BLACK;
                DrawHelper.drawString(stack, font, toDisplay, 27.5f, 20, color.getRGB());
            }
        }
    }
    private static void renderNearbyLunarDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Lunar lunar = Lunar.of(player);
            if (lunar != null) {
                String toDisplay = getLunarDisplay(lunar);
                Color color = Color.magenta;
                DrawHelper.drawString(stack, font, toDisplay, 27.5f, 30, color.getRGB());
            }
        }
    }

    private static void renderNearbyShieldsDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Shields shields = Shields.of(player);
            if (shields != null) {
                String toDisplay = getShieldsDisplay(shields);
                Color color = Color.BLUE;
                DrawHelper.drawString(stack, font, toDisplay, 27.5f, 30, color.getRGB());
            }
        }
    }

    private static String getMoneyDisplay(Money money) {
        int currentMoney = money.getCurrentMoney();
        return I18n.get("riskofmine.currentmoney.name") + currentMoney;

    }

    private static String getLunarDisplay(Lunar lunar) {
        int currentLunar = lunar.getCurrentLunar();
        return I18n.get("riskofmine.currentlunar.name") + currentLunar;

    }

    private static String getShieldsDisplay(Shields shields) {
        int shieldsCurrent = shields.getCurrentShields();
        return I18n.get("riskofmine.currentshields.name") + shieldsCurrent;

    }

    private static String getBarrierDisplay(Barrier barrier) {
        int barrierCurrent = barrier.getCurrentBarrier();
        return I18n.get("riskofmine.currentbarrier.name") + barrierCurrent;

    }
}
