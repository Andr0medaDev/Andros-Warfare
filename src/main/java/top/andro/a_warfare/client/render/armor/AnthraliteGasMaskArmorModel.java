package top.andro.a_warfare.client.render.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.item.animated.AnthraliteGasMaskArmorItem;

public class AnthraliteGasMaskArmorModel extends GeoModel<AnthraliteGasMaskArmorItem> {
    @Override
    public ResourceLocation getModelResource(AnthraliteGasMaskArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "geo/anthralite_respirator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnthraliteGasMaskArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/armor/anthralite_respirator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnthraliteGasMaskArmorItem animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/anthralite_gas_mask.animation.json");
    }
}