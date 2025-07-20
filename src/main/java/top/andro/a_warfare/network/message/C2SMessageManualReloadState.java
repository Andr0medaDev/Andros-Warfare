package top.andro.a_warfare.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.common.ReloadTracker;
import top.andro.a_warfare.common.ReloadType;
import top.andro.a_warfare.init.ModSyncedDataKeys;
import top.andro.a_warfare.item.animated.AnimatedGunItem;

public class C2SMessageManualReloadState extends PlayMessage<C2SMessageManualReloadState> {
        private final ReloadState state;
        private boolean complete;

        public enum ReloadState {
            START,
            LOAD,
            STOP;
        }

        public C2SMessageManualReloadState() {
            this.state = ReloadState.START;
            this.complete = false;
        }

        public C2SMessageManualReloadState(ReloadState state, boolean complete) {
            this.state = state;
            this.complete = complete;
        }

        @Override
        public void encode(C2SMessageManualReloadState message, FriendlyByteBuf buffer) {
            buffer.writeEnum(message.state);
            buffer.writeBoolean(message.complete);
        }

        @Override
        public C2SMessageManualReloadState decode(FriendlyByteBuf buffer) {
            return new C2SMessageManualReloadState(buffer.readEnum(ReloadState.class), buffer.readBoolean());
        }


        @Override
        public void handle(C2SMessageManualReloadState message, MessageContext context) {
            context.execute(() -> {
                ServerPlayer player = context.getPlayer();
                if (player == null || player.isSpectator()) return;

                ItemStack heldItem = player.getMainHandItem();
                if (!(heldItem.getItem() instanceof AnimatedGunItem gunItem)) return;

                Gun gun = gunItem.getModifiedGun(heldItem);
                if (gun.getReloads().getReloadType() != ReloadType.MANUAL) return;

                heldItem.getOrCreateTag();

                switch (message.state) {
                    case START:
                        ModSyncedDataKeys.RELOADING.setValue(player, true);
                        break;

                    case LOAD:
                        ReloadTracker tracker = new ReloadTracker(player);
                        tracker.increaseAmmo(player);
                        break;

                    case STOP:
                        if (message.complete) {
                            ModSyncedDataKeys.RELOADING.setValue(player, false);
                        }
                        break;
                }
            });
            context.setHandled(true);
        }
    }