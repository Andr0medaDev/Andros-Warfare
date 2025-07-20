package top.andro.a_warfare.client.render.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.andro.a_warfare.item.animated.NetheriteGasMaskArmorItem;

public class NetheriteGasMaskArmorRenderer extends GeoArmorRenderer<NetheriteGasMaskArmorItem> {
    public NetheriteGasMaskArmorRenderer() {
        super(new NetheriteGasMaskArmorModel());
    }
}