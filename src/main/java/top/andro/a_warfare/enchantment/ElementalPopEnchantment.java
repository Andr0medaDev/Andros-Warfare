package top.andro.a_warfare.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

/**
 * Author: MrCrayfish
 */
public class ElementalPopEnchantment extends GunEnchantment
{
    public ElementalPopEnchantment()
    {
        super(Rarity.VERY_RARE, EnchantmentTypes.TRIGGER_FINGER_COMPATIBLE , new EquipmentSlot[]{EquipmentSlot.MAINHAND}, Type.WEAPON);

    }

    @Override
    public int getMaxLevel()
    {
        return 2;
    }

    @Override
    public int getMinCost(int level)
    {
        return 15 + (level - 1) * 10;
    }

    @Override
    public int getMaxCost(int level)
    {
        return this.getMinCost(level) + 40;
    }
}
