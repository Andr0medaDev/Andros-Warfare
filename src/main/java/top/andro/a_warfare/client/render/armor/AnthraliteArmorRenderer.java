package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.AnthraliteArmorItem;

public class AnthraliteArmorRenderer extends GeoArmorRenderer<AnthraliteArmorItem> {
    public AnthraliteArmorRenderer() {
        super(new AnthraliteArmorModel());
    }
}