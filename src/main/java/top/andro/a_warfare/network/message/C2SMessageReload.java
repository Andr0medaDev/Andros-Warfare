package top.andro.a_warfare.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.client.handler.ReloadHandler;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.common.ReloadType;
import top.andro.a_warfare.init.ModSyncedDataKeys;
import top.andro.a_warfare.item.GunItem;
import top.andro.a_warfare.item.animated.AnimatedGunItem;

/**
 * Author: MrCrayfish
 */
public class C2SMessageReload extends PlayMessage<C2SMessageReload>
{
    private boolean reload;

    public C2SMessageReload() {}

    public C2SMessageReload(boolean reload)
    {
        this.reload = reload;
    }

    @Override
    public void encode(C2SMessageReload message, FriendlyByteBuf buffer)
    {
        buffer.writeBoolean(message.reload);
    }

    @Override
    public C2SMessageReload decode(FriendlyByteBuf buffer)
    {
        return new C2SMessageReload(buffer.readBoolean());
    }

    @Override
    public void handle(C2SMessageReload message, MessageContext context) {
        context.execute(() -> {
            ServerPlayer player = context.getPlayer();
            if (player != null && !player.isSpectator()) {
                ItemStack heldItem = player.getMainHandItem();
                if (!(heldItem.getItem() instanceof GunItem) ||
                        !heldItem.getItem().getClass().getPackageName().startsWith("top.andro.a_warfare")) {
                    return;
                }

                ModSyncedDataKeys.RELOADING.setValue(player, message.reload);

                if (!message.reload) {
                    if (heldItem.getItem() instanceof AnimatedGunItem) {
                        CompoundTag tag = heldItem.getOrCreateTag();
                        Gun gun = ((GunItem) heldItem.getItem()).getModifiedGun(heldItem);

                        if (gun.getReloads().getReloadType() == ReloadType.MANUAL &&
                                tag.getBoolean("IsReloading") &&
                                !tag.getBoolean("a_warfare:IsPlayingReloadStop")) {
                            tag.putBoolean("a_warfare:IsPlayingReloadStop", true);
                            ReloadHandler.loaded(player);
                        }
                    }
                }
            }
        });
        context.setHandled(true);
    }
}