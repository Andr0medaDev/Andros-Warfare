package top.andro.a_warfare.item;

import top.andro.a_warfare.entity.throwable.ThrowableGrenadeEntity;
import top.andro.a_warfare.entity.throwable.ThrowableStunGrenadeEntity;
import top.andro.a_warfare.init.ModSounds;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

/**
 * Author: MrCrayfish
 */
public class StunGrenadeItem extends GrenadeItem
{
    public StunGrenadeItem(Item.Properties properties, int maxCookTime)
    {
        super(properties, maxCookTime);
    }

    @Override
    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft)
    {
        return new ThrowableStunGrenadeEntity(world, entity, 20 * 2);
    }

    @Override
    public boolean canCook()
    {
        return false;
    }

    @Override
    protected void onThrown(Level world, ThrowableGrenadeEntity entity)
    {
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.ITEM_GRENADE_PIN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
