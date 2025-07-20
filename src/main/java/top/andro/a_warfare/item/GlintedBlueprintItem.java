package top.andro.a_warfare.item;

import net.minecraft.world.item.ItemStack;

public class GlintedBlueprintItem extends BlueprintItem {
    public GlintedBlueprintItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
    @Override
    public boolean isFireResistant() {
        return true;
    }
}


