package top.andro.a_warfare.interfaces;

import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.common.ProjectileManager;
import top.andro.a_warfare.entity.projectile.ProjectileEntity;
import top.andro.a_warfare.item.GunItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * This class allows weapons to fire custom projectiles instead of the default implementation. The
 * grenade launcher uses this to spawn a grenade entity with custom physics. Use {@link ProjectileManager}
 * to register a factory.
 *
 * Author: MrCrayfish
 */
public interface IProjectileFactory
{
    /**
     * Creates a new projectile entity.
     *
     * @param worldIn     the world the projectile is going to be spawned into
     * @param entity      the entity who fired the weapon
     * @param weapon      the item stack of the weapon
     * @param item        the gun item
     * @param modifiedGun the properties of the gun
     * @return a projectile entity
     */
    ProjectileEntity create(Level worldIn, LivingEntity entity, ItemStack weapon, GunItem item, Gun modifiedGun);
}
