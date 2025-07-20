package top.andro.a_warfare.item.ammo_boxes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.item.AmmoBoxItem;

public class RockPouch extends AmmoBoxItem {
    private static final int ROCKS_MAX_ITEM_COUNT = 3200;
    private static final int ROCKS_BAR_COLOR = Mth.color(0.4F, 0.4F, 0.7F);

    public RockPouch(Properties properties) {
        super(properties);
    }

    @Override
    protected ResourceLocation getAmmoTag() {
        return new ResourceLocation("a_warfare", "rocks");
    }


    @Override
    public int getBarColor(ItemStack stack) {
        return ROCKS_BAR_COLOR;
    }

    @Override
    protected int getBaseMaxItemCount() {
        return ROCKS_MAX_ITEM_COUNT;
    }

}
