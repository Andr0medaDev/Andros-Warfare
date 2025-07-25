package top.andro.a_warfare.item.ammo_boxes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.item.AmmoBoxItem;

public class RocketAmmoBoxItem extends AmmoBoxItem {
    private static final int ROCKET_MAX_ITEM_COUNT = 256;
    private static final int ROCKET_BAR_COLOR = Mth.color(0.4F, 0.4F, 0.7F);

    public RocketAmmoBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ResourceLocation getAmmoTag() {
        return new ResourceLocation("a_warfare", "rocket_ammo");
    }


    @Override
    public int getBarColor(ItemStack stack) {
        return ROCKET_BAR_COLOR;
    }

    @Override
    protected int getBaseMaxItemCount() {
        return ROCKET_MAX_ITEM_COUNT;
    }

}
