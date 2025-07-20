package top.andro.a_warfare.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.entity.monster.HornlinEntity;

public class HornlinRenderer extends MobRenderer<HornlinEntity, HornlinModel<HornlinEntity>> {
    public HornlinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HornlinModel<>(pContext.bakeLayer(ModModelLayers.HORNLIN_LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(HornlinEntity pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/hornlin.png");
    }

    @Override
    public void render(HornlinEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}






