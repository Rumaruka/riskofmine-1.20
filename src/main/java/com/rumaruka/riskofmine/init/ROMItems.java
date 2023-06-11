package com.rumaruka.riskofmine.init;

import com.google.common.collect.Lists;
import com.rumaruka.riskofmine.common.items.common.*;
import com.rumaruka.riskofmine.common.items.eqiupment.BlastShowerItem;
import com.rumaruka.riskofmine.common.items.eqiupment.TheCrowdFunderItem;
import com.rumaruka.riskofmine.common.items.gameplay.LunarCoinItem;
import com.rumaruka.riskofmine.common.items.legendary.AlienHeadItem;
import com.rumaruka.riskofmine.common.items.legendary.DioBestFriendItem;
import com.rumaruka.riskofmine.common.items.lunar.BeadsOfFealtyItem;
import com.rumaruka.riskofmine.common.items.lunar.ShapedGlassItem;
import com.rumaruka.riskofmine.common.items.uncommon.ChronobaubleItem;
import com.rumaruka.riskofmine.common.items.uncommon.HopooFeatherItem;
import com.rumaruka.riskofmine.common.items.uncommon.InfusionItem;
import com.rumaruka.riskofmine.common.items.uncommon.OldWarStealthkitItem;
import com.rumaruka.riskofmine.common.items.voiditems.SaferSpacesItem;
import com.rumaruka.riskofmine.common.items.voiditems.TentabaubleItem;
import com.rumaruka.riskofmine.common.items.voiditems.WeepingFungusItem;
import net.minecraft.world.item.Item;
import ru.timeconqueror.timecore.api.registry.ItemRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.ArrayList;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static com.rumaruka.riskofmine.RiskOfMine.tl;
import static ru.timeconqueror.timecore.api.util.Hacks.promise;

@AutoRegistrable.Entries("item")
public class ROMItems {
    public static ArmorPiercingRoundsItem ARMOR_PIERCING_ROUNDS = promise();
    public static BustlingFungusItem BUSTLING_FUNGUS = promise();

    public static GasolineItem GASOLINE = promise();
    public static InfusionItem INFUSION = promise();
    public static ShapedGlassItem SHAPED_GLASS = promise();
    public static SoldierSyringeItem SOLDIER_SYRINGE = promise();
    public static MonsterToothItem MONSTER_TOOTH = promise();
    public static CrowbarItem CROWBAR = promise();
    public static EnergyDrinkItem ENERGY_DRINK = promise();
    public static BeadsOfFealtyItem BEADS_OF_FEALTY = promise();
    public static ChronobaubleItem CHRONOBAUBLE = promise();
    public static BlastShowerItem BLAST_SHOWER = promise();
    public static FocusCrystalItem FOCUS_CRYSTAL = promise();
    public static DioBestFriendItem DIO_BEST_FRIEND = promise();
    public static AlienHeadItem ALIEN_HEAD = promise();
    public static OldWarStealthkitItem OLD_WAR_STEALTHKIT = promise();
    public static TriTipDaggerItem TRI_TIP_DAGGER = promise();

    public static HopooFeatherItem HOPOO_FEATHER = promise();
    public static StunGrenadeItem STUN_GRENADE = promise();
    public static WarbannerItem WARBANNER = promise();
    public static TheCrowdFunderItem THE_CROWDFUNDER = promise();
    public static StickyBombItem STICKY_BOMB = promise();
    public static TopazBroochItem TOPAZ_BROOCH = promise();
    public static TentabaubleItem TENTABAUBLE = promise();
    public static BisonSteakItem BISON_STEAK = promise();

    //    // public static CommonItemScrapItem COMMON_ITEM_SCRAP   = promise();
//    //  public static UnCommonItemScrapItem UNCOMMON_ITEM_SCRAP   = promise();
//
    public static WeepingFungusItem WEEPING_FUNGUS = promise();

    public static PowerElixirItem POWER_ELIXIR = promise();
    public static EmptyElixirItem EMPTY_ELIXIR = promise();
    public static TougherTimesItem TOUGHER_TIMES = promise();

    public static SaferSpacesItem SAFER_SPACES = promise();


    public static LunarCoinItem LUNAR_COIN = promise();


    private static class Init {
        @AutoRegistrable
        private static final ItemRegister REGISTER = new ItemRegister(MODID);

        @AutoRegistrable.Init
        private static void register() {

            REGISTER.register("armor_piercing_rounds", ArmorPiercingRoundsItem::new).defaultModel(tl("item/armor_piercing_rounds"));
            REGISTER.register("bustling_fungus", BustlingFungusItem::new).defaultModel(tl("item/bustling_fungus"));
            REGISTER.register("gasoline", GasolineItem::new).defaultModel(tl("item/gasoline"));
            REGISTER.register("infusion", InfusionItem::new).defaultModel(tl("item/infusion"));
            REGISTER.register("shaped_glass", ShapedGlassItem::new).defaultModel(tl("item/shaped_glass"));
            REGISTER.register("soldier_syringe", SoldierSyringeItem::new).defaultModel(tl("item/soldier_syringe"));
            REGISTER.register("monster_tooth", MonsterToothItem::new).defaultModel(tl("item/monster_tooth"));
            REGISTER.register("crowbar", CrowbarItem::new).defaultModel(tl("item/crowbar"));
            REGISTER.register("energy_drink", EnergyDrinkItem::new).defaultModel(tl("item/energy_drink"));
            REGISTER.register("beads_of_fealty", BeadsOfFealtyItem::new).defaultModel(tl("item/beads_of_fealty"));
            REGISTER.register("chronobauble", ChronobaubleItem::new).defaultModel(tl("item/chronobauble"));
            REGISTER.register("blast_shower", BlastShowerItem::new).defaultModel(tl("item/blast_shower"));
            REGISTER.register("focus_crystal", FocusCrystalItem::new).defaultModel(tl("item/focus_crystal"));
            REGISTER.register("dio_best_friend", DioBestFriendItem::new).defaultModel(tl("item/dio_best_friend"));
            REGISTER.register("alien_head", AlienHeadItem::new).defaultModel(tl("item/alien_head"));
            REGISTER.register("old_war_stealthkit", OldWarStealthkitItem::new).defaultModel(tl("item/old_war_stealthkit"));
            REGISTER.register("tri_tip_dagger", TriTipDaggerItem::new).defaultModel(tl("item/tri_tip_dagger"));
            REGISTER.register("stun_grenade", StunGrenadeItem::new).defaultModel(tl("item/stun_grenade"));
            REGISTER.register("warbanner", WarbannerItem::new).defaultModel(tl("item/warbanner"));
            REGISTER.register("the_crowdfunder", TheCrowdFunderItem::new).defaultModel(tl("item/the_crowdfunder"));
            REGISTER.register("sticky_bomb", StickyBombItem::new).defaultModel(tl("item/sticky_bomb"));
            REGISTER.register("tentabauble", TentabaubleItem::new).defaultModel(tl("item/tentabauble"));
            REGISTER.register("topaz_brooch", TopazBroochItem::new).defaultModel(tl("item/topaz_broosh"));
            REGISTER.register("bison_steak", BisonSteakItem::new).defaultModel(tl("item/bison_steak"));
            // REGISTER.register("common_item_scrap", CommonItemScrapItem::new).defaultModel(tl("item/common_item_scrap"));
            // REGISTER.register("uncommon_item_scrap", UnCommonItemScrapItem::new).defaultModel(tl("item/uncommon_item_scrap"));
            REGISTER.register("weeping_fungus", WeepingFungusItem::new).defaultModel(tl("item/weeping_fungus"));
            REGISTER.register("power_elixir", PowerElixirItem::new).defaultModel(tl("item/power_elixir"));
            REGISTER.register("empty_elixir", EmptyElixirItem::new).defaultModel(tl("item/empty_bottle"));
            REGISTER.register("tougher_times", TougherTimesItem::new).defaultModel(tl("item/tougher_times"));
            REGISTER.register("safer_spaces", SaferSpacesItem::new).defaultModel(tl("item/safer_spaces"));
            REGISTER.register("hopoo_feather", HopooFeatherItem::new).defaultModel(tl("item/hopoo_feather"));

            REGISTER.register("lunar_coin", LunarCoinItem::new);


        }
    }

    public static ArrayList<Item> getAllItem() {
        return (Lists.newArrayList(

                ARMOR_PIERCING_ROUNDS,
                BUSTLING_FUNGUS,
                GASOLINE,
                INFUSION,
                SHAPED_GLASS,
                SOLDIER_SYRINGE,
                MONSTER_TOOTH,
                CROWBAR,
                ENERGY_DRINK,
                BEADS_OF_FEALTY,
                CHRONOBAUBLE,
                BLAST_SHOWER,
                FOCUS_CRYSTAL,
                DIO_BEST_FRIEND,
                ALIEN_HEAD,
                OLD_WAR_STEALTHKIT,
                TRI_TIP_DAGGER,
                STUN_GRENADE,
                WARBANNER,
                THE_CROWDFUNDER,
                STICKY_BOMB,
                TOPAZ_BROOCH,
                TENTABAUBLE,
                BISON_STEAK,
                // COMMON_ITEM_SCRAP,
                //  UNCOMMON_ITEM_SCRAP,
                HOPOO_FEATHER,
                WEEPING_FUNGUS,
                POWER_ELIXIR,
                EMPTY_ELIXIR,
                TOUGHER_TIMES,
                SAFER_SPACES,

                LUNAR_COIN
        ));
    }
}
