package com.rumaruka.riskofmine.compat.jei;

import com.rumaruka.riskofmine.init.ROMItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.rumaruka.riskofmine.RiskOfMine.rl;


@JeiPlugin
public class ROMJeiPlugin implements IModPlugin {
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ROMItems.getAllItem().forEach(item -> {
            registerIngredientInfo(registration, item);
        });
    }


    @Override
    public ResourceLocation getPluginUid() {
        return rl("riskofmine");
    }

    public void registerIngredientInfo(IRecipeRegistration registration, Item ingredient) {

        registration.addIngredientInfo(new ItemStack(ingredient), VanillaTypes.ITEM_STACK,
                Component.translatable("jei." + ingredient.getDescriptionId() + ".desc"));


    }
}
