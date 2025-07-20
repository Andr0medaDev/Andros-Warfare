package top.andro.a_warfare.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

/**
 * Author: MrCrayfish
 */
public class WaterProofEnchantment extends GunEnchantment
{
    public WaterProofEnchantment()
    {
        super(Rarity.COMMON, EnchantmentTypes.WATER_PROOF_COMPATIBLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}, Type.PROJECTILE);
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public int getMinCost(int level)
    {
        return 10 + (level - 1) * 10;
    }

    @Override
    public int getMaxCost(int level)
    {
        return this.getMinCost(level) + 20;
    }
}
