package top.andro.a_warfare.entity.projectile;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShieldItem;
import top.andro.a_warfare.Config;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.item.GunItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

/**
 * Author: MrCrayfish
 */
public class RocketEntity extends ProjectileEntity
{
    private static final float SHIELD_DISABLE_CHANCE = 1.0f; // 100% chance to disable shield

    public RocketEntity(EntityType<? extends ProjectileEntity> entityType, Level worldIn)
    {
        super(entityType, worldIn);
    }

    public RocketEntity(EntityType<? extends ProjectileEntity> entityType, Level worldIn, LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun)
    {
        super(entityType, worldIn, shooter, weapon, item, modifiedGun);
    }

    @Override
    protected void onProjectileTick()
    {
        if (this.level().isClientSide)
        {
            for (int i = 5; i > 0; i--)
            {
                this.level().addParticle(ParticleTypes.CLOUD, true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
            }
            if (this.level().random.nextInt(2) == 0)
            {
                this.level().addParticle(ParticleTypes.SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                this.level().addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }

    @Override
    protected void onHitEntity(Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec, boolean headshot)
    {
        // Always attempt to disable the shield
        if (entity instanceof Player player)
        {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();

            boolean isBlockingMainHand = player.isBlocking() && mainHandItem.getItem() instanceof ShieldItem;
            boolean isBlockingOffHand = player.isBlocking() && offHandItem.getItem() instanceof ShieldItem;

            if (isBlockingMainHand || isBlockingOffHand)
            {
                ItemStack shield = isBlockingMainHand ? mainHandItem : offHandItem;
                InteractionHand hand = isBlockingMainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;

                player.getCooldowns().addCooldown(shield.getItem(), 100);
                player.stopUsingItem();
                player.level().broadcastEntityEvent(player, (byte)30);

                // Play shield break sound
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.SHIELD_BREAK, SoundSource.PLAYERS, 1.0F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);

                shield.hurtAndBreak(15, player, (p) -> p.broadcastBreakEvent(hand));
            }
        }

        // Create explosion
        createExplosion(this, Config.COMMON.rockets.explosionRadius.get().floatValue(), false);
    }

    @Override
    protected void onHitBlock(BlockState state, BlockPos pos, Direction face, double x, double y, double z)
    {
        createExplosion(this, Config.COMMON.rockets.explosionRadius.get().floatValue(), false);
    }

    @Override
    public void onExpired()
    {
        createExplosion(this, Config.COMMON.rockets.explosionRadius.get().floatValue(), false);
    }
}
