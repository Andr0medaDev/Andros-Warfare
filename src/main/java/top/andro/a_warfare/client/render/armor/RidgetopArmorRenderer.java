package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.RidgetopArmorItem;

public class RidgetopArmorRenderer extends GeoArmorRenderer<RidgetopArmorItem> {
    public RidgetopArmorRenderer() {
        super(new RidgetopArmorModel());
    }
}