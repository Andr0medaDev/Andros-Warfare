package top.andro.a_warfare.item.attachment;

import top.andro.a_warfare.item.attachment.impl.Magazine;

public interface IMagazine extends IAttachment<Magazine>
{
    /**
     * @return The type of this attachment
     */
    @Override
    default Type getType()
    {
        return Type.MAGAZINE;
    }
}
