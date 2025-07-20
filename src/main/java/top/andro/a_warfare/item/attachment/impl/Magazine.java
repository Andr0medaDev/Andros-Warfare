package top.andro.a_warfare.item.attachment.impl;

import top.andro.a_warfare.interfaces.IGunModifier;

public class Magazine extends Attachment
{
    private Magazine(IGunModifier... modifier)
    {
        super(modifier);
    }

    /**
     * Creates an magazine get
     *
     * @param modifier an array of gun modifiers
     * @return an magazine get
     */
    public static Magazine create(IGunModifier... modifier)
    {
        return new Magazine(modifier);
    }
}

