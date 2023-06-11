package com.rumaruka.riskofmine.api;

import net.minecraft.ChatFormatting;
import net.minecraft.util.StringRepresentable;

public enum Category implements StringRepresentable {
    DAMAGE("Damage", "damage", ChatFormatting.DARK_RED),
    SCRAP("Scrap", "scrap", ChatFormatting.GRAY),
    UTILITY("Utility", "utility", ChatFormatting.GOLD),
    HEALING("Healing", "healing", ChatFormatting.GREEN),

    ;
    private final String name;
    private final String unlocalizedName;
    private final ChatFormatting chatColor;

    Category(String name, String unlocalizedName, ChatFormatting chatColor) {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.chatColor = chatColor;
    }

    @Override
    public String getSerializedName() {
        return unlocalizedName;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getChatColor() {
        return chatColor;
    }
}
