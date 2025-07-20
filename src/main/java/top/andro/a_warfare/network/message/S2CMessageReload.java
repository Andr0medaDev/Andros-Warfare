package top.andro.a_warfare.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.network.FriendlyByteBuf;
import top.andro.a_warfare.client.network.ClientPlayHandler;

public class S2CMessageReload extends PlayMessage<S2CMessageReload> {
    private boolean reloading;

    public S2CMessageReload() {}

    public S2CMessageReload(boolean reloading) {
        this.reloading = reloading;
    }

    @Override
    public void encode(S2CMessageReload message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.reloading);
    }

    @Override
    public S2CMessageReload decode(FriendlyByteBuf buffer) {
        return new S2CMessageReload(buffer.readBoolean());
    }

    @Override
    public void handle(S2CMessageReload message, MessageContext context) {
        context.execute(() -> {
            ClientPlayHandler.handleReloadState(message.reloading);
        });
        context.setHandled(true);
    }

    public boolean isReloading() {
        return this.reloading;
    }
}