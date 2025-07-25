package top.andro.a_warfare.client.render.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.item.animated.CogKnightArmorItem;

public class CogKnightArmorModel extends GeoModel<CogKnightArmorItem> {
    @Override
    public ResourceLocation getModelResource(CogKnightArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "geo/cog_knight_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CogKnightArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/armor/cog_knight_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CogKnightArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/cog_knight_armor.animation.json");
    }
}