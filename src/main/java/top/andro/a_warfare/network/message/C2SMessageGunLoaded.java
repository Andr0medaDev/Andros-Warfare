package top.andro.a_warfare.network.message;

import com.mrcrayfish.framework.api.network.LevelLocation;
import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.common.ReloadTracker;
import top.andro.a_warfare.common.ReloadType;
import top.andro.a_warfare.init.ModSyncedDataKeys;
import top.andro.a_warfare.item.GunItem;
import top.andro.a_warfare.network.PacketHandler;
import top.andro.a_warfare.util.GunModifierHelper;

public class C2SMessageGunLoaded extends PlayMessage<C2SMessageGunLoaded> {
    public C2SMessageGunLoaded() {}

    public void encode(C2SMessageGunLoaded message, FriendlyByteBuf buffer) {}

    public C2SMessageGunLoaded decode(FriendlyByteBuf buffer) {
        return new C2SMessageGunLoaded();
    }

    @Override
    public void handle(C2SMessageGunLoaded message, MessageContext context) {
        context.execute(() -> {
            ServerPlayer player = context.getPlayer();
            if (player != null && !player.isSpectator()) {
                ItemStack heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof GunItem) {
                    if (!heldItem.getItem().getClass().getPackageName().startsWith("top.andro.a_warfare")) {
                        return;
                    }
                    Gun gun = ((GunItem)heldItem.getItem()).getModifiedGun(heldItem);
                    CompoundTag tag = heldItem.getOrCreateTag();

                    ReloadTracker tracker = new ReloadTracker(player);

                    if (gun.getReloads().getReloadType() == ReloadType.MAG_FED) {
                        tracker.increaseMagAmmo(player);  // This will properly consume ammo
                        tag.putBoolean("a_warfare:ReloadComplete", true);
                        tag.putBoolean("a_warfare:IsPlayingReloadStop", true);
                        tag.putString("a_warfare:ReloadState", "STOPPING");
                        tag.remove("IsReloading");
                        tag.remove("a_warfare:IsReloading");
                        ModSyncedDataKeys.RELOADING.setValue(player, false);
                        PacketHandler.getPlayChannel().sendToNearbyPlayers(
                                () -> LevelLocation.create(player.level(), player.getX(), player.getY(), player.getZ(), 64),
                                new S2CMessageUpdateAmmo(tag.getInt("AmmoCount"))
                        );
                        PacketHandler.getPlayChannel().sendToNearbyPlayers(
                                () -> LevelLocation.create(player.level(), player.getX(), player.getY(), player.getZ(), 64),
                                new S2CMessageReload(false)
                        );
                    } else if (gun.getReloads().getReloadType() == ReloadType.SINGLE_ITEM) {
                        tracker.reloadItem(player);
                        tag.putBoolean("a_warfare:ReloadComplete", true);
                        tag.putBoolean("a_warfare:IsPlayingReloadStop", true);
                        tag.putString("a_warfare:ReloadState", "STOPPING");
                        tag.remove("IsReloading");
                        tag.remove("a_warfare:IsReloading");
                        ModSyncedDataKeys.RELOADING.setValue(player, false);
                        PacketHandler.getPlayChannel().sendToNearbyPlayers(
                                () -> LevelLocation.create(player.level(), player.getX(), player.getY(), player.getZ(), 64),
                                new S2CMessageUpdateAmmo(tag.getInt("AmmoCount"))
                        );
                        PacketHandler.getPlayChannel().sendToNearbyPlayers(
                                () -> LevelLocation.create(player.level(), player.getX(), player.getY(), player.getZ(), 64),
                                new S2CMessageReload(false)
                        );
                    } else if (gun.getReloads().getReloadType() == ReloadType.MANUAL) {
                        tracker.increaseAmmo(player);

                        PacketHandler.getPlayChannel().sendToNearbyPlayers(
                                () -> LevelLocation.create(player.level(), player.getX(), player.getY(), player.getZ(), 64),
                                new S2CMessageUpdateAmmo(tag.getInt("AmmoCount"))
                        );

                        int currentAmmo = tag.getInt("AmmoCount");
                        int maxAmmo = GunModifierHelper.getModifiedAmmoCapacity(heldItem, gun);

                        if (currentAmmo >= maxAmmo) {
                            tag.putBoolean("a_warfare:ReloadComplete", true);
                            tag.putBoolean("a_warfare:IsPlayingReloadStop", true);
                            tag.putString("a_warfare:ReloadState", "STOPPING");
                            tag.remove("IsReloading");
                            tag.remove("a_warfare:IsReloading");
                            ModSyncedDataKeys.RELOADING.setValue(player, false);
                            PacketHandler.getPlayChannel().sendToNearbyPlayers(
                                    () -> LevelLocation.create(player.level(), player.getX(), player.getY(), player.getZ(), 64),
                                    new S2CMessageReload(false)
                            );
                        }
                    }
                }
            }
        });
        context.setHandled(true);
    }
}