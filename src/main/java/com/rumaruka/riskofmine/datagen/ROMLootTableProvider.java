package com.rumaruka.riskofmine.datagen;

import com.mojang.blaze3d.MethodsReturnNonnullByDefault;
import com.rumaruka.riskofmine.datagen.chests.ChestLootTableROM;
import com.rumaruka.riskofmine.datagen.loot.ROMLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataId;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;

import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;


@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ROMLootTableProvider extends LootTableProvider {

    private final List<SubProviderEntry> lootTables = List.of(new LootTableProvider.SubProviderEntry(ChestLootTableROM::new, LootContextParamSets.CHEST));

    public ROMLootTableProvider(PackOutput pOutputs) {
        super(pOutputs, ROMLootTables.all(), List.of(new LootTableProvider.SubProviderEntry(ChestLootTableROM::new, LootContextParamSets.CHEST)));
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {
        map.forEach((resourceLocation, lootTable) -> lootTable.validate(validationcontext.setParams(lootTable.getParamSet()).enterElement("{" + resourceLocation + "}", new LootDataId<>(LootDataType.TABLE, resourceLocation))));

    }
}
