package top.andro.a_warfare.item.ammo_boxes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.item.AmmoBoxItem;

import java.util.Optional;

public class CreativeAmmoBoxItem extends AmmoBoxItem {
    private static final int CREATIVE_BAR_COLOR = Mth.color(0.4F, 0.4F, 0.7F);

    public CreativeAmmoBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public ResourceLocation getAmmoTag() {
        return new ResourceLocation("a_warfare", "ammo");
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return CREATIVE_BAR_COLOR;
    }

    @Override
    protected int getBaseMaxItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return false;
    }

    public static int add(ItemStack pouchStack, ItemStack insertedStack) {
        if (pouchStack.getItem() instanceof CreativeAmmoBoxItem) {
            return 0;
        }
        return AmmoBoxItem.add(pouchStack, insertedStack);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack otherStack, Slot slot, ClickAction action, Player player, SlotAccess slotAccess) {
        return false;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.empty();
    }
}