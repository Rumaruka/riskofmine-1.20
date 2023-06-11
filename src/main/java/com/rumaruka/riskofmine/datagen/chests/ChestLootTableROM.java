package com.rumaruka.riskofmine.datagen.chests;

import com.rumaruka.riskofmine.datagen.loot.ROMLootTables;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ChestLootTableROM implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(ROMLootTables.SMALL_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ROMItems.ARMOR_PIERCING_ROUNDS).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.CROWBAR).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.SOLDIER_SYRINGE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.BUSTLING_FUNGUS).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.ENERGY_DRINK).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.FOCUS_CRYSTAL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.GASOLINE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.MONSTER_TOOTH).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.STUN_GRENADE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ROMItems.TRI_TIP_DAGGER).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))));
    }
}
