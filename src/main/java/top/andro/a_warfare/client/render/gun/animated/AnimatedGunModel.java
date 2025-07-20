package top.andro.a_warfare.client.render.gun.animated;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import top.andro.a_warfare.item.animated.AnimatedGunItem;


public class AnimatedGunModel extends DefaultedItemGeoModel<AnimatedGunItem> {
    private final String modelPath;

    public AnimatedGunModel(ResourceLocation path) {
        super(path);
        this.modelPath = path.getPath();
    }

    @Override
    public ResourceLocation getModelResource(AnimatedGunItem gunItem) {
        return new ResourceLocation("a_warfare", "geo/item/gun/" + modelPath + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedGunItem gunItem) {
        return new ResourceLocation("a_warfare", "textures/animated/gun/" + modelPath + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedGunItem gunItem) {
        return new ResourceLocation("a_warfare", "animations/item/" + modelPath + ".animation.json");
    }
}