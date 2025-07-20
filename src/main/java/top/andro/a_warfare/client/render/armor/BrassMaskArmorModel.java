package top.andro.a_warfare.client.render.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.item.animated.BrassMaskArmorItem;

public class BrassMaskArmorModel extends GeoModel<BrassMaskArmorItem> {
    @Override
    public ResourceLocation getModelResource(BrassMaskArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "geo/brass_mask.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BrassMaskArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/armor/brass_mask.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BrassMaskArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/brass_mask.animation.json");
    }
}