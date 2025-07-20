package top.andro.a_warfare.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.entity.monster.DissidentEntity;

public class DissidentRenderer extends MobRenderer<DissidentEntity, DissidentModel<DissidentEntity>> {
    public DissidentRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DissidentModel<>(pContext.bakeLayer(ModModelLayers.DISSIDENT_LAYER)), 0.7f);
    }
    @Override
    public ResourceLocation getTextureLocation(DissidentEntity pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/dissident.png");
    }
}


