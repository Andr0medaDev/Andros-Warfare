package top.andro.a_warfare.item.attachment;

import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.item.attachment.impl.Attachment;

import javax.annotation.Nullable;

/**
 * The base attachment interface
 * <p>
 * Author: MrCrayfish
 */
public interface IAttachment<T extends Attachment>
{
    /**
     * @return The type of this attachment
     */
    Type getType();

    /**
     * @return The additional properties about this attachment
     */
    T getProperties();

    /**
     * @param stack Weapon stack
     * @return If attachment can be attached to gun
     */
    default boolean canAttachTo(ItemStack stack)
    {
        return true;
    }

    enum Type
    {
        SCOPE("scope", "Scope", "scope"),
        BARREL("barrel", "Barrel", "barrel"),
        STOCK("stock", "Stock", "stock"),
        UNDER_BARREL("under_barrel", "Under_Barrel", "underBarrel"),
        MAGAZINE("magazine", "Magazine", "magazine");

        private final String translationKey;
        private final String tagKey;
        private final String serializeKey;

        Type(String translationKey, String tagKey, String serializeKey)
        {
            this.translationKey = translationKey;
            this.tagKey = tagKey;
            this.serializeKey = serializeKey;
        }

        public String getTranslationKey()
        {
            return this.translationKey;
        }

        public String getTagKey()
        {
            return this.tagKey;
        }

        public String getSerializeKey()
        {
            return this.serializeKey;
        }

        @Nullable
        public static Type byTagKey(String s)
        {
            for(Type type : values())
            {
                if(type.tagKey.equalsIgnoreCase(s))
                {
                    return type;
                }
            }
            return null;
        }

        public String getName() {
            return this.tagKey;
        }
    }
}
