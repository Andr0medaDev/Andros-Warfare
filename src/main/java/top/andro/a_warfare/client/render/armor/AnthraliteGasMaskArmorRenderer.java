package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.AnthraliteGasMaskArmorItem;

public class AnthraliteGasMaskArmorRenderer extends GeoArmorRenderer<AnthraliteGasMaskArmorItem> {
    public AnthraliteGasMaskArmorRenderer() {
        super(new AnthraliteGasMaskArmorModel());
    }
}