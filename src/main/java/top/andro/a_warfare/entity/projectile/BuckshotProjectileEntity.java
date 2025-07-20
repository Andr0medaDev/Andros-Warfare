package top.andro.a_warfare.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.init.ModItems;
import top.andro.a_warfare.item.GunItem;
import top.andro.a_warfare.util.GunEnchantmentHelper;
import top.andro.a_warfare.util.GunModifierHelper;

public class BuckshotProjectileEntity extends ProjectileEntity {
    private static final float BULLET_DROP_CHANCE = 0.0F;

    public BuckshotProjectileEntity(EntityType<? extends Entity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public BuckshotProjectileEntity(EntityType<? extends Entity> entityType, Level worldIn, LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun) {
        super(entityType, worldIn, shooter, weapon, item, modifiedGun);
    }

    @Override
    protected void onHitBlock(BlockState state, BlockPos pos, Direction face, double x, double y, double z) {
        super.onHitBlock(state, pos, face, x, y, z);

        if (!this.level().isClientSide && this.random.nextFloat() < BULLET_DROP_CHANCE) {
            ItemEntity bulletEntity = new ItemEntity(
                    this.level(),
                    this.getX(), this.getY(), this.getZ(),
                    new ItemStack(ModItems.STANDARD_BULLET.get())
            );

            double bounceStrength = 0.1;
            bulletEntity.setDeltaMovement(
                    (this.random.nextDouble() - 0.5) * bounceStrength,
                    0.15,
                    (this.random.nextDouble() - 0.5) * bounceStrength
            );

            this.level().addFreshEntity(bulletEntity);
        }
    }

    @Override
    public float getDamage() {
        float damage = getaFloat();
        damage = GunModifierHelper.getModifiedDamage(this.getWeapon(), this.modifiedGun, damage);
        damage = GunEnchantmentHelper.getAcceleratorDamage(this.getWeapon(), damage);
        damage = GunEnchantmentHelper.getHeavyShotDamage(this.getWeapon(), damage);
        damage = GunEnchantmentHelper.getHotBarrelDamage(this.getWeapon(), damage);
        return Math.max(0F, damage);
    }

    @Override
    protected void onHitEntity(Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec, boolean headshot) {
        entity.invulnerableTime = 0;

        super.onHitEntity(entity, hitVec, startVec, endVec, headshot);

        if (!this.level().isClientSide && this.random.nextFloat() < BULLET_DROP_CHANCE) {
            ItemEntity bulletEntity = new ItemEntity(
                    this.level(),
                    this.getX(), this.getY(), this.getZ(),
                    new ItemStack(ModItems.STANDARD_BULLET.get())
            );

            double bounceStrength = 0.1;
            bulletEntity.setDeltaMovement(
                    (this.random.nextDouble() - 0.5) * bounceStrength,
                    0.15,
                    (this.random.nextDouble() - 0.5) * bounceStrength
            );

            this.level().addFreshEntity(bulletEntity);
        }
    }
}