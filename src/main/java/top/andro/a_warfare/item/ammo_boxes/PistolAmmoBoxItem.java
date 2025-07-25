package top.andro.a_warfare.item.ammo_boxes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.item.AmmoBoxItem;

public class PistolAmmoBoxItem extends AmmoBoxItem {
    private static final int PISTOL_MAX_ITEM_COUNT = 1024;
    private static final int PISTOL_BAR_COLOR = Mth.color(0.4F, 0.4F, 0.7F);

    public PistolAmmoBoxItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    protected ResourceLocation getAmmoTag() {
        return new ResourceLocation("a_warfare", "pistol_ammo");
    }


    @Override
    public int getBarColor(ItemStack stack) {
        return PISTOL_BAR_COLOR;
    }

    @Override
    protected int getBaseMaxItemCount() {
        return PISTOL_MAX_ITEM_COUNT;
    }

}
