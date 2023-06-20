package com.rumaruka.riskofmine.datagen.loot;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.Set;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class ROMLootTables {

    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
    private static final Set<ResourceLocation> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);

    public static final ResourceLocation SMALL_CHEST = register("chests/overworld/small_chest");
    public static final ResourceLocation LARGE_CHEST = register("chests/large_chest");
    public static final ResourceLocation LEGENDARY_CHEST = register("chests/legendary_chest");
    public static final ResourceLocation LUNAR_CHEST = register("chests/lunar_chest");

    public static final ResourceLocation EQUIPMENT_BARREL = register("chests/equipment_barrel");
    //Category Chest
    public static final ResourceLocation DAMAGE_CHEST = register("chests/damage_chest");
    public static final ResourceLocation HEALING_CHEST = register("chests/healing_chest");
    public static final ResourceLocation UTILITY_CHEST = register("chests/utility_chest");


    private static ResourceLocation register(String id) {
        return register(rl(id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (LOCATIONS.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

    public static Set<ResourceLocation> all() {
        return IMMUTABLE_LOCATIONS;
    }

}
