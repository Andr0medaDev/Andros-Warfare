package top.andro.a_warfare.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.entity.monster.SupplyScampEntity;

public class SupplyScampRenderer extends MobRenderer<SupplyScampEntity, SupplyScampModel<SupplyScampEntity>> {
    public SupplyScampRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SupplyScampModel<>(pContext.bakeLayer(ModModelLayers.SUPPLY_SCAMP_LAYER)), 0.7f);
    }
    @Override
    public ResourceLocation getTextureLocation(SupplyScampEntity pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/supply_scamp.png");
    }
}


