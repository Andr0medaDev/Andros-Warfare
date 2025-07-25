package top.andro.a_warfare.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.init.ModItems;

/**
 * Author: MrCrayfish
 */
public class AttachmentItem extends Item implements IMeta
{
    public AttachmentItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return false;
    }

    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(ModItems.REPAIR_KIT.get());
    }
}
