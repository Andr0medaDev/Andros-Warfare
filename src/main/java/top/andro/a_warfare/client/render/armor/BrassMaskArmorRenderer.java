package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.BrassMaskArmorItem;

public class BrassMaskArmorRenderer extends GeoArmorRenderer<BrassMaskArmorItem> {
    public BrassMaskArmorRenderer() {
        super(new BrassMaskArmorModel());
    }
}