package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.CogKnightArmorItem;

public class CogKnightArmorRenderer extends GeoArmorRenderer<CogKnightArmorItem> {
    public CogKnightArmorRenderer() {
        super(new CogKnightArmorModel());
    }
}