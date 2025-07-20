package top.andro.a_warfare.entity.client;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import top.andro.a_warfare.entity.projectile.BrassBoltEntity;

public class BrassBoltRenderer extends ArrowRenderer<BrassBoltEntity> {
    public BrassBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BrassBoltEntity entity) {
        return new ResourceLocation("a_warfare", "textures/entity/projectiles/brass_bolt.png");
    }
}
