package top.andro.a_warfare.item.ammo_boxes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.item.AmmoBoxItem;

public class RifleAmmoBoxItem extends AmmoBoxItem {
    private static final int RIFLE_MAX_ITEM_COUNT = 768;
    private static final int RIFLE_BAR_COLOR = Mth.color(0.4F, 0.4F, 0.7F);

    public RifleAmmoBoxItem(Item.Properties properties) {
        super(properties);
    }
    @Override
    protected ResourceLocation getAmmoTag() {
        return new ResourceLocation("a_warfare", "rifle_ammo");
    }


    @Override
    public int getBarColor(ItemStack stack) {
        return RIFLE_BAR_COLOR;
    }

    @Override
    protected int getBaseMaxItemCount() {
        return RIFLE_MAX_ITEM_COUNT;
    }

}

