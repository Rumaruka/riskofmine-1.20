package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import com.rumaruka.riskofmine.common.entity.MalachiteUrchinsEntity;
import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.common.entity.weapon.GoldenBulletEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import ru.timeconqueror.timecore.api.registry.EntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static ru.timeconqueror.timecore.api.util.Hacks.promise;

@AutoRegistrable.Entries("entity_type")
public class ROMEntity {

    public static EntityType<HealthOrbEntity> HEALTH_ORB = promise();
    public static EntityType<StickyBombEntity> STICKY_BOMB = promise();
    public static EntityType<MalachiteUrchinsEntity> MALACHITE_URCHINS = promise();
    public static EntityType<GoldenBulletEntity> GOLDEN_BULLET = promise();

    @AutoRegistrable
    private static final EntityRegister REGISTER = new EntityRegister(MODID);

    @AutoRegistrable.Init
    private static void register() {
        REGISTER.register("health_orb",
                EntityType.Builder.<HealthOrbEntity>of(HealthOrbEntity::new, MobCategory.MISC)
                        .setTrackingRange(80)
                        .setShouldReceiveVelocityUpdates(true)
                        .sized(3.5F, 3.5F).updateInterval(20));

        REGISTER.register("sticky_bomb",
                EntityType.Builder.<StickyBombEntity>of(StickyBombEntity::new, MobCategory.MISC)
                        .setTrackingRange(80)
                        .setShouldReceiveVelocityUpdates(true)
                        .sized(3.5F, 3.5F).updateInterval(20));
        REGISTER.register("golden_bullet",
                EntityType.Builder.<GoldenBulletEntity>of(GoldenBulletEntity::new, MobCategory.MISC)
                        .setTrackingRange(80)
                        .setShouldReceiveVelocityUpdates(true)
                        .sized(3.5F, 3.5F).updateInterval(20));
        REGISTER.register("malachite_urchins",
                EntityType.Builder.<MalachiteUrchinsEntity>of(MalachiteUrchinsEntity::new, MobCategory.MISC)
                        .setTrackingRange(80)
                        .setShouldReceiveVelocityUpdates(true)
                        .sized(3.5F, 3.5F).updateInterval(20));
    }
}
