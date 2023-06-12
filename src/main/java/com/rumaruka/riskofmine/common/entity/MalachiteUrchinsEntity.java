package com.rumaruka.riskofmine.common.entity;

import com.rumaruka.riskofmine.events.PlayerHealthEvent;
import com.rumaruka.riskofmine.init.ROMEffects;
import com.rumaruka.riskofmine.init.ROMEntity;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.init.ROMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MalachiteUrchinsEntity extends Entity {

    private int age;
    private int health = 1;
    public int value;
    private int count = 1;
    private Player followingPlayer;

    public MalachiteUrchinsEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ROMEntity.MALACHITE_URCHINS, pLevel);
        this.setPos(pX, pY, pZ);
        this.setYRot((float) (this.random.nextDouble() * 360.0D));
        this.setDeltaMovement((this.random.nextDouble() * (double) 0.2F - (double) 0.1F) * 2.0D, this.random.nextDouble() * 0.2D * 2.0D, (this.random.nextDouble() * (double) 0.2F - (double) 0.1F) * 2.0D);

    }

    public MalachiteUrchinsEntity(EntityType<? extends MalachiteUrchinsEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @NotNull
    protected Entity. MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    protected void defineSynchedData() {
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        if (this.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
            this.setUnderwaterMovement();
        } else if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
        }

        if (this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
            this.setDeltaMovement(((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), 0.2F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        }

        if (!this.level().noCollision(this.getBoundingBox())) {
            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
        }

        if (this.tickCount % 20 == 1) {
            this.scanForEntities();
        }

        if (this.followingPlayer != null && (this.followingPlayer.isSpectator() || this.followingPlayer.isDeadOrDying())) {
            this.followingPlayer = null;
        }

        if (this.followingPlayer != null) {
            Vec3 vec3 = new Vec3(this.followingPlayer.getX() - this.getX(), this.followingPlayer.getY() + (double) this.followingPlayer.getEyeHeight() / 2.0D - this.getY(), this.followingPlayer.getZ() - this.getZ());
            double d0 = vec3.lengthSqr();
            if (d0 < 64.0D) {
                double d1 = 1.0D - Math.sqrt(d0) / 8.0D;
                this.setDeltaMovement(this.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * 0.1D)));
            }
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        float f = 0.98F;
        if (this.onGround()) {
            BlockPos pos = new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ());
            f = this.level().getBlockState(pos).getFriction(this.level(), pos, this) * 0.98F;
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply((double) f, 0.98D, (double) f));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, -0.9D, 1.0D));
        }

        ++this.age;
        if (this.age >= 6000) {
            this.discard();
        }

    }

    private void scanForEntities() {
        if (this.followingPlayer == null || this.followingPlayer.distanceToSqr(this) > 64.0D) {
            this.followingPlayer = this.level().getNearestPlayer(this, 8.0D);
        }

        if (this.level() instanceof ServerLevel) {
            for (MalachiteUrchinsEntity experienceorb : this.level().getEntities(EntityTypeTest.forClass(MalachiteUrchinsEntity.class), this.getBoundingBox().inflate(0.5D), this::canMerge)) {
                this.merge(experienceorb);
            }
        }

    }




    private boolean canMerge(MalachiteUrchinsEntity p_147087_) {
        return p_147087_ != this && canMerge(p_147087_, this.getId(), this.value);
    }

    private static boolean canMerge(MalachiteUrchinsEntity pOrb, int pAmount, int pOther) {
        return !pOrb.isRemoved() && (pOrb.getId() - pAmount) % 40 == 0 && pOrb.value == pOther;
    }

    private void merge(MalachiteUrchinsEntity pOrb) {
        this.count += pOrb.count;
        this.age = Math.min(this.age, pOrb.age);
        pOrb.discard();
    }

    private void setUnderwaterMovement() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x * (double) 0.99F, Math.min(vec3.y + (double) 5.0E-4F, (double) 0.06F), vec3.z * (double) 0.99F);
    }


    protected void doWaterSplashEffect() {
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.level().isClientSide || this.isRemoved()) return false; //Forge: Fixes MC-53850
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else if (this.level().isClientSide) {
            return true;
        } else {
            this.markHurt();
            this.health = (int) ((float) this.health - pAmount);
            if (this.health <= 0) {
                this.discard();
            }

            return true;
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putShort("Health", (short) this.health);
        pCompound.putShort("Age", (short) this.age);
        pCompound.putShort("Value", (short) this.value);
        pCompound.putInt("Count", this.count);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.health = pCompound.getShort("Health");
        this.age = pCompound.getShort("Age");
        this.value = pCompound.getShort("Value");
        this.count = Math.max(pCompound.getInt("Count"), 1);
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void playerTouch(Player pEntity) {
        if (!this.level().isClientSide) {
            if (pEntity.takeXpDelay == 0) {

                pEntity.addEffect(new MobEffectInstance(ROMEffects.MALACHITE.get()));

                if (this.count == 0) {
                    this.discard();
                }
            }

        }
    }


    private int durabilityToXp(int pDurability) {
        return pDurability / 2;
    }

    private int xpToDurability(int pXp) {
        return pXp * 2;
    }

    /**
     * Returns the XP value of this XP orb.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns a number from 1 to 10 based on how much XP this orb is worth. This is used by RenderXPOrb to determine
     * what texture to use.
     */
    public int getIcon() {
        if (this.value >= 2477) {
            return 10;
        } else if (this.value >= 1237) {
            return 9;
        } else if (this.value >= 617) {
            return 8;
        } else if (this.value >= 307) {
            return 7;
        } else if (this.value >= 149) {
            return 6;
        } else if (this.value >= 73) {
            return 5;
        } else if (this.value >= 37) {
            return 4;
        } else if (this.value >= 17) {
            return 3;
        } else if (this.value >= 7) {
            return 2;
        } else {
            return this.value >= 3 ? 1 : 0;
        }
    }

    /**
     * Get a fragment of the maximum experience points value for the supplied value of experience points value.
     */
    public static int getExperienceValue(int pExpValue) {
        if (pExpValue >= 2477) {
            return 2477;
        } else if (pExpValue >= 1237) {
            return 1237;
        } else if (pExpValue >= 617) {
            return 617;
        } else if (pExpValue >= 307) {
            return 307;
        } else if (pExpValue >= 149) {
            return 149;
        } else if (pExpValue >= 73) {
            return 73;
        } else if (pExpValue >= 37) {
            return 37;
        } else if (pExpValue >= 17) {
            return 17;
        } else if (pExpValue >= 7) {
            return 7;
        } else {
            return pExpValue >= 3 ? 3 : 1;
        }
    }

    /**
     * Returns {@code true} if it's possible to attack this entity with an item.
     */
    public boolean isAttackable() {
        return false;
    }

    @NotNull
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @NotNull
    public SoundSource getSoundSource() {
        return SoundSource.AMBIENT;
    }
}

