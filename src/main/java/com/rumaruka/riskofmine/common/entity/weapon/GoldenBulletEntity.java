package com.rumaruka.riskofmine.common.entity.weapon;

import com.rumaruka.riskofmine.init.ROMEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class GoldenBulletEntity extends Projectile {
    public GoldenBulletEntity(EntityType<? extends GoldenBulletEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void defineSynchedData() {
    }
}
