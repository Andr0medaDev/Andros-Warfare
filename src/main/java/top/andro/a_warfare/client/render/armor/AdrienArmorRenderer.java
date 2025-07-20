package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.AdrienArmorItem;

public class AdrienArmorRenderer extends GeoArmorRenderer<AdrienArmorItem> {
    public AdrienArmorRenderer() {
        super(new AdrienArmorModel());
    }
}