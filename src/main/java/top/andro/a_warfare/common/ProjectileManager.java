package top.andro.a_warfare.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import top.andro.a_warfare.entity.projectile.ProjectileEntity;
import top.andro.a_warfare.init.ModEntities;
import top.andro.a_warfare.interfaces.IProjectileFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to manage custom projectile factories
 *
 * Author: MrCrayfish
 */
public class ProjectileManager
{
    private static ProjectileManager instance = null;

    public static ProjectileManager getInstance()
    {
        if(instance == null)
        {
            instance = new ProjectileManager();
        }
        return instance;
    }

    private final IProjectileFactory DEFAULT_FACTORY = (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun);
    //private final IProjectileFactory SPECTRE_FACTORY = (worldIn, entity, weapon, item, modifiedGun) -> new SpectreProjectileEntity(ModEntities.SPECTRE_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun);
    //private final IProjectileFactory WATER_FACTORY = (worldIn, entity, weapon, item, modifiedGun) -> new WaterProjectileEntity(ModEntities.WATER_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun);
    private final Map<ResourceLocation, IProjectileFactory> projectileFactoryMap = new HashMap<>();

    /**
     * Registers a projectile factory for the given item. This allows for control over the entity
     * that is spawned when a weapon, that uses the given ammo, is fired.
     *
     * @param ammo    the ammo item
     * @param factory a custom projectile implementation
     */
    public void registerFactory(Item ammo, IProjectileFactory factory)
    {
        this.projectileFactoryMap.put(ForgeRegistries.ITEMS.getKey(ammo), factory);
    }

    /**
     * Gets the projectile factory for the given resource location.
     *
     * @param id the resource id of the projectile factory (the id of the item)
     * @return the custom projectile factory or the default factory if nothing exists for the id
     */
    public IProjectileFactory getFactory(ResourceLocation id)
    {
        return this.projectileFactoryMap.getOrDefault(id, DEFAULT_FACTORY);
    }
}
