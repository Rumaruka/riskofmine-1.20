package com.rumaruka.riskofmine.utils;

import com.rumaruka.riskofmine.init.ROMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ROMDoubleEffect {
    private final static Random r = new Random();

    public static void play(Player player) {
        play(player, player);
    }

    public static void play(Player localPlayer, Player effectPlayer) {
        Level world = localPlayer.level();
        world.playSound(null, new BlockPos( effectPlayer.getBlockX(), effectPlayer.getBlockY(), effectPlayer.getBlockZ()), ROMSounds.ROM_PLAYER_FEATHER.get(), SoundSource.MASTER, 2, 2);


        for (int i = 0; i < 5; ++i) {
            double d = r.nextGaussian() * 0.02D;
            double e = r.nextGaussian() * 0.02D;
            double f = r.nextGaussian() * 0.02D;
            world.addParticle(ParticleTypes.CLOUD, effectPlayer.getX(1.0D), effectPlayer.getY(), effectPlayer.getZ(1.0D), d, e, f);
        }
    }
}
