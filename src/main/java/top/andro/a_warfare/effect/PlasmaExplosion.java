package top.andro.a_warfare.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.andro.a_warfare.entity.projectile.ProjectileEntity;

public class PlasmaExplosion extends CustomExplosion {
    private static final float OWNER_DAMAGE_REDUCTION = 0.1F;
    private final RandomSource random = RandomSource.create();
    private final Entity source;

    public PlasmaExplosion(Level pLevel, @Nullable Entity pSource, double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius, boolean pFire, CustomBlockInteraction customBlockInteraction) {
        super(pLevel, pSource, pToBlowX, pToBlowY, pToBlowZ, pRadius, pFire, customBlockInteraction);
        this.source = pSource;
    }

    @Override
    public void explode() {
        super.explode();

        // Particle effects on client side
        if (this.level.isClientSide) {
            for (int i = 0; i < 100; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 2.0;
                double offsetY = (this.random.nextDouble() - 0.5) * 2.0;
                double offsetZ = (this.random.nextDouble() - 0.5) * 2.0;
                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.x + offsetX, this.y + offsetY, this.z + offsetZ, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void finalizeExplosion(boolean pSpawnParticles) {
        if (!this.level.isClientSide) {
            DamageSource damageSource = this.getDamageSource();

            LivingEntity shooter = null;
            if (this.source instanceof ProjectileEntity projectile) {
                shooter = projectile.getShooter();
            }

            // Handle damage for each affected entity
            for (LivingEntity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(this.radius * 2.0))) {
                if (!entity.isAlive()) continue;

                double distance = Math.sqrt(entity.distanceToSqr(this.x, this.y, this.z)) / this.radius;
                if (distance > 1.0D) continue;

                double exposure = Explosion.getSeenPercent(this.getPosition(), entity);
                if (exposure <= 0.0D) continue;

                float damage = (float) ((1.0D - distance) * exposure * this.radius * 2.0);

                // Apply damage reduction if the entity is the shooter
                if (entity == shooter) {
                    damage *= OWNER_DAMAGE_REDUCTION;
                }

                entity.hurt(damageSource, damage);

                // Apply knockback
                double deltaX = entity.getX() - this.x;
                double deltaZ = entity.getZ() - this.z;
                double distance2D = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                if (distance2D > 0.0D) {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(
                            deltaX / distance2D * exposure * 0.3D,
                            exposure * 0.3D,
                            deltaZ / distance2D * exposure * 0.3D
                    ));
                }
            }
        }

        super.finalizeExplosion(pSpawnParticles);
    }

    public @NotNull Vec3 getPosition() {
        return new Vec3(this.x, this.y, this.z);
    }

    private AABB getBoundingBox() {
        return new AABB(
                this.x - this.radius,
                this.y - this.radius,
                this.z - this.radius,
                this.x + this.radius,
                this.y + this.radius,
                this.z + this.radius
        );
    }
}

