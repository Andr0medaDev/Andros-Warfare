package top.andro.a_warfare.client.render.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.item.animated.RidgetopArmorItem;

public class RidgetopArmorModel extends GeoModel<RidgetopArmorItem> {
    @Override
    public ResourceLocation getModelResource(RidgetopArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "geo/ridgetop.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RidgetopArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/armor/ridgetop.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RidgetopArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/ridgetop.animation.json");
    }
}