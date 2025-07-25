package top.andro.a_warfare.network.message;

import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.Validate;
import top.andro.a_warfare.client.network.ClientPlayHandler;
import top.andro.a_warfare.common.CustomGun;
import top.andro.a_warfare.common.CustomGunLoader;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.common.NetworkGunManager;

/**
 * Author: MrCrayfish
 */
public class S2CMessageUpdateGuns extends PlayMessage<S2CMessageUpdateGuns>
{
    private ImmutableMap<ResourceLocation, Gun> registeredGuns;
    private ImmutableMap<ResourceLocation, CustomGun> customGuns;

    public S2CMessageUpdateGuns() {}

    @Override
    public void encode(S2CMessageUpdateGuns message, FriendlyByteBuf buffer)
    {
        Validate.notNull(NetworkGunManager.get());
        Validate.notNull(CustomGunLoader.get());
        NetworkGunManager.get().writeRegisteredGuns(buffer);
        CustomGunLoader.get().writeCustomGuns(buffer);
    }

    @Override
    public S2CMessageUpdateGuns decode(FriendlyByteBuf buffer)
    {
        S2CMessageUpdateGuns message = new S2CMessageUpdateGuns();
        message.registeredGuns = NetworkGunManager.readRegisteredGuns(buffer);
        message.customGuns = CustomGunLoader.readCustomGuns(buffer);
        return message;
    }

    @Override
    public void handle(S2CMessageUpdateGuns message, MessageContext context)
    {
        context.execute(() -> ClientPlayHandler.handleUpdateGuns(message));
        context.setHandled(true);
    }

    public ImmutableMap<ResourceLocation, Gun> getRegisteredGuns()
    {
        return this.registeredGuns;
    }

    public ImmutableMap<ResourceLocation, CustomGun> getCustomGuns()
    {
        return this.customGuns;
    }
}