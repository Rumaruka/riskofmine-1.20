package com.rumaruka.riskofmine.common.tiles;

import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class WarbannerTE extends BlockEntity {
    public WarbannerTE(BlockPos pPos, BlockState pBlockState) {
        super(ROMTiles.WARBANNER_BLOCK, pPos, pBlockState);
    }


    public static void tick(Level level, BlockPos pPos, BlockState pState, WarbannerTE pBlockEntity) {

        AABB pArea = new AABB(pPos.getX(), pPos.getY(), pPos.getZ(), pPos.getX(), pPos.getY(), pPos.getZ());
        List<Player> entities = level.getEntitiesOfClass(
                Player.class, pArea);
        for (Player entity : entities) {

            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED));
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST));


        }
    }

}
