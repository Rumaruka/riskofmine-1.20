package com.rumaruka.riskofmine.common.events;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.init.*;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.ItemActivationPacket;
import com.rumaruka.riskofmine.utils.ROMDoubleEffect;
import com.rumaruka.riskofmine.utils.ROMMathFormula;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.text.AttributedString;
import java.util.UUID;



@Mod.EventBusSubscriber
public class ItemsEvents {

    public static final UUID healthModifierID = UUID.fromString("208b4d4c-50ef-4b45-a097-4bed633cdbff");
    private static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");


    /**
     * onPlayerHurt  - for hurt entites without Player event
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onEntityHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level level = livingEntity.level();
            if (!level.isClientSide) {

                if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.ARMOR_PIERCING_ROUNDS))) {
                    if ((event.getEntity() instanceof WitherBoss || event.getEntity() instanceof EnderDragon || !event.getEntity().canChangeDimensions())) {
                        event.getEntity().hurt(DamageSource.MAGIC, ROMUtils.counting(player, new ItemStack(ROMItems.ARMOR_PIERCING_ROUNDS)) * 2 - 1);

                    }
                }
                if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.CROWBAR))) {
                    if (event.getEntity() instanceof AmbientCreature) {
                        if (((AmbientCreature) event.getEntity()).getHealth() > (((AmbientCreature) event.getEntity()).getMaxHealth() * 90 / 100)) {

                            event.getEntity().hurt(DamageSource.MAGIC, (float) (ROMUtils.counting(player, new ItemStack(ROMItems.CROWBAR)) * 1.00115d));
                        }
                    }
                }
                if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.GASOLINE))) {
                    if (event.getEntity() instanceof AmbientCreature) {


                        event.getEntity().setRemainingFireTicks(ROMUtils.counting(player, new ItemStack(ROMItems.GASOLINE)) * 20);
                    }
                }
                if (ROMUtils.checkCurios(player, new ItemStack(ROMItems.ARMOR_PIERCING_ROUNDS))) {
                    ItemStack curioStack = ROMUtils.curiosItemStack(player, ROMItems.ARMOR_PIERCING_ROUNDS);

                    if (curioStack.getItem() == ROMItems.ARMOR_PIERCING_ROUNDS && event.getEntity() instanceof AmbientCreature) {
                        if (curioStack.getItem() == ROMItems.ARMOR_PIERCING_ROUNDS && (event.getEntity() instanceof WitherBoss || event.getEntity() instanceof EnderDragon || !event.getEntity().canChangeDimensions())) {
                            event.getEntity().hurt(DamageSource.MAGIC, curioStack.getCount() * 2 - 1);
                        }
                    }
                }
                if (ROMUtils.checkCurios(player, new ItemStack(ROMItems.CROWBAR))) {
                    ItemStack curioStack = ROMUtils.curiosItemStack(player, ROMItems.CROWBAR);

                    if (((AmbientCreature) event.getEntity()).getHealth() > (((AmbientCreature) event.getEntity()).getMaxHealth() * 90 / 100)) {

                        event.getEntity().hurt(DamageSource.MAGIC, (float) (curioStack.getCount() * 1.00115d));
                    }
                }

            }
            if (ROMUtils.checkCurios(player, new ItemStack(ROMItems.GASOLINE))) {
                ItemStack curioStack = ROMUtils.curiosItemStack(player, ROMItems.GASOLINE);

                if (curioStack.getItem() == ROMItems.GASOLINE && event.getEntity() instanceof AmbientCreature) {
                    event.getEntity().setRemainingFireTicks(curioStack.getCount() * 20);
                }

            }
            if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.STICKY_BOMB))) {
                StickyBombEntity entityStickyBomb = new StickyBombEntity(level, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, player, (Mob) event.getEntity());
                level.addFreshEntity(entityStickyBomb);
            }
            if (ROMUtils.checkCurios(player, new ItemStack(ROMItems.STICKY_BOMB))) {
                StickyBombEntity entityStickyBomb = new StickyBombEntity(level, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, player, (Mob) event.getEntity());
                level.addFreshEntity(entityStickyBomb);

            }

        }
    }


    @SubscribeEvent
    public static void onPlayerLevelUp(PlayerXpEvent.LevelChange event) {
        Player entity = event.getEntity();
        if (ROMUtils.checkInventory(entity, new ItemStack(ROMItems.WARBANNER))) {
            entity.level().setBlockAndUpdate(entity.blockPosition(), ROMBlocks.WARBANNER_BLOCK.defaultBlockState());
        }


    }


    /**
     * onPlayerHurt  - for hurt player event
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerHurt(LivingHurtEvent event) {

        if (event.getSource().getEntity() instanceof Mob && event.getEntity() instanceof ServerPlayer player) {


            Level world = player.level;
            if (!world.isClientSide) {

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack itemStack = player.getInventory().getItem(i);
                    if (itemStack.getItem() == ROMItems.OLD_WAR_STEALTHKIT) {
                        if (player.getHealth() >= 2.5f) {
                            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1000, 1, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 1, false, false));
                        }

                    }

                }
                if (CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.OLD_WAR_STEALTHKIT).isPresent()) {
                    ItemStack curioStack = CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.OLD_WAR_STEALTHKIT).get().stack();
                    if (curioStack.getItem() == ROMItems.OLD_WAR_STEALTHKIT) {
                        if (curioStack.getItem() == ROMItems.OLD_WAR_STEALTHKIT) {
                            if (player.getHealth() >= 2.5f) {
                                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1000, 1, false, false));
                                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 1, false, false));
                            }
                        }
                    }
                }

            }


        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        /**
         Player kill Entity
         */
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level world = livingEntity.level;

            if (!world.isClientSide) {

                if (CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.MONSTER_TOOTH).isPresent()) {
                    ItemStack curiosStack = CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.MONSTER_TOOTH).get().stack();
                    world.addFreshEntity(new HealthOrbEntity(world, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, curiosStack.getCount()));
                    world.playSound(null, new BlockPos(player.getX(), player.getY(), player.getZ()), ROMSounds.PROC_MT_SPAWN.get(), SoundSource.MASTER, 2, 2);
                }

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack itemStack = player.getInventory().getItem(i);
                    if (itemStack.getItem() == ROMItems.MONSTER_TOOTH) {
                        world.addFreshEntity(new HealthOrbEntity(world, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, itemStack.getCount()));
                        world.playSound(null, new BlockPos(player.getX(), player.getY(), player.getZ()), ROMSounds.PROC_MT_SPAWN.get(), SoundSource.MASTER, 2, 2);

                    }
                }

            }
        }
        /**
         Entity kill Player
         */
        if (event.getSource().getEntity() instanceof AmbientCreature livingEntity && event.getEntity() instanceof ServerPlayer player) {
            Level world = player.level;
            if (!world.isClientSide) {
                if (event.getSource().isBypassInvul()) {
                    return;
                } else {
                    for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                        ItemStack itemStack = player.getInventory().getItem(i);
                        if (itemStack.getItem() == ROMItems.DIO_BEST_FRIEND) {
                            if (player.isDeadOrDying() || player.getHealth() < 2.5f) {
                                player.setHealth(player.getMaxHealth());
                                player.removeAllEffects();
                                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1800, 2));
                                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 2));
                                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600, 1));
                                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 600, 1));
                                world.broadcastEntityEvent(player, (byte) 35);
                                ROMNetwork.getInstance().sendTo(new ItemActivationPacket(itemStack), player);


                                itemStack.shrink(1);


                            }
                        }


                    }
                    if (CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.DIO_BEST_FRIEND).isPresent()) {
                        ItemStack curiosStack = CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.DIO_BEST_FRIEND).get().stack();
                        if (curiosStack.getItem() == ROMItems.DIO_BEST_FRIEND) {
                            if (player.isDeadOrDying() || player.getHealth() < 2.5f) {

                                player.setHealth(player.getMaxHealth());
                                player.removeAllEffects();
                                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1800, 2));
                                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 2));
                                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600, 1));
                                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 600, 1));
                                world.broadcastEntityEvent(player, (byte) 35);
                                ROMNetwork.getInstance().sendTo(new ItemActivationPacket(curiosStack), player);
                                curiosStack.shrink(1);
                            }

                        }
                    }


                }


            }
        }
    }

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingTickEvent event) {
        Player player = ROMUtils.getPlayer();
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level;
        if (!level.isClientSide) {
            if (player != null) {

                if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.FOCUS_CRYSTAL))) {
                    if (livingEntity instanceof Mob) {
                        float distance = player.distanceTo(livingEntity);

                        if (distance <= 3.5) {
                            livingEntity.hurt(DamageSource.MAGIC, ROMMathFormula.powerIncreasing(ROMUtils.counting(player, new ItemStack(ROMItems.FOCUS_CRYSTAL)), 5.0f, 5));
                            ROMUtils.getMc().particleEngine.createTrackingEmitter(livingEntity, ROMParticles.FOCUS_CRYSTAL.get());
                        }


                    }
                }


                if (CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.FOCUS_CRYSTAL).isPresent()) {
                    ItemStack curioStack = CuriosApi.getCuriosHelper().findFirstCurio(player, ROMItems.FOCUS_CRYSTAL).get().stack();
                    ;
                    if (livingEntity instanceof Mob) {
                        float distance = player.distanceTo(livingEntity);

                        if (distance <= 3.5) {
                            livingEntity.hurt(DamageSource.MAGIC, curioStack.getCount());
                            ROMUtils.getMc().particleEngine.createTrackingEmitter(livingEntity, ROMParticles.FOCUS_CRYSTAL.get(), 20);
                        }
                    }

                }



            }

        }
    }


    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.HOPOO_FEATHER))) {

                ROMDoubleEffect.play(player);


            }
        }
    }




}
