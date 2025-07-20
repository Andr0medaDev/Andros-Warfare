package top.andro.a_warfare.network;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.andro.a_warfare.init.ModSyncedDataKeys;
import top.andro.a_warfare.item.GunItem;
import top.andro.a_warfare.util.GunModifierHelper;
@OnlyIn(Dist.CLIENT)
public class ClientMessageHandler {
    public static boolean handleUpdateAmmo(int ammoCount) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.getItem() instanceof GunItem) {
                CompoundTag tag = heldItem.getOrCreateTag();
                tag.putInt("AmmoCount", ammoCount);
                int maxAmmo = GunModifierHelper.getModifiedAmmoCapacity(heldItem, ((GunItem)heldItem.getItem()).getModifiedGun(heldItem));
                if (ammoCount >= maxAmmo && tag.getBoolean("a_warfare:IsReloading")) {
                    tag.putString("a_warfare:ReloadState", "STOPPING");
                    tag.putBoolean("a_warfare:IsPlayingReloadStop", true);
                    ModSyncedDataKeys.RELOADING.setValue(player, false);
                    tag.remove("a_warfare:IsReloading");
                }
            }
        }
        return true;
    }
}