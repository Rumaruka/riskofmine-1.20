package com.rumaruka.riskofmine.api;

import com.rumaruka.riskofmine.init.ROMBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public enum Chest implements StringRepresentable {
    LUNAR("lunar",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    RUSTY("rusty",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    EQUIPMENT_TRIPLE_BARREL("equipment_triple_barrel",3, 1, 184, 184, rl("textures/gui/dirt_triple_container.png"), 256, 256),
    MULTI_SHOP("multi_shop",3, 1, 184, 184, rl("textures/gui/dirt_triple_container.png"), 256, 256),
    EQUIPMENT_BARREL("equipment_barrel",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    LEGENDARY("legendary",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    LARGE("large",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    SMALL("small",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    DAMAGE("damage",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    HEALING("healing",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    UTILITY("utility",1, 1, 184, 184, rl("textures/gui/dirt_container.png"), 256, 256),
    NULL_SIZE("",0, 0, 0, 0, null, 0, 0);

    private final String name;
    public final int size;

    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;

    Chest(int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this(null, size, rowLength, xSize, ySize, guiTexture, textureXSize, textureYSize);
    }

    Chest(String name, int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this.name = name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public String getId() {
        return this.getName().toLowerCase(Locale.ROOT);
    }

    public String getEnglishName() {
        return this.name;
    }


    @Override
    public String getSerializedName() {
        return this.getEnglishName();
    }

    @NotNull
    public String getName() {
        return name;
    }

    public static Block get(Chest type) {
        switch (type) {
            case SMALL:
                return ROMBlocks.SMALL_CHEST;
            case LARGE:
                return ROMBlocks.LARGE_CHEST;
            case LEGENDARY:
                return ROMBlocks.LEGENDARY_CHEST;
//            case EQUIPMENT_BARREL:
//                return ROMBlocks.EQUIPMENT_BARREL;
//            case RUSTY:
//                return ROMBlocks.RUSTY_CHEST;
//            case DAMAGE:
//                return ROMBlocks.DAMAGE_CHEST;
//            case HEALING:
//                return ROMBlocks.HEALING_CHEST;
//            case UTILITY:
//                return ROMBlocks.UTILITY_CHEST;
            case LUNAR:
                return ROMBlocks.LUNAR_CHEST;
            case MULTI_SHOP:
                return ROMBlocks.MULTI_SHOP;
            case EQUIPMENT_TRIPLE_BARREL:
                return ROMBlocks.EQUIPMENT_TRIPLE_BARREL;
            default:
                return Blocks.CHEST;
        }
    }

}
