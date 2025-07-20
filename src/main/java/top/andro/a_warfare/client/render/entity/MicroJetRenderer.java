package top.andro.a_warfare.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import top.andro.a_warfare.client.SpecialModels;
import top.andro.a_warfare.client.util.RenderUtil;
import top.andro.a_warfare.entity.projectile.MicroJetEntity;

/**
 * Author: MrCrayfish
 */
public class MicroJetRenderer extends EntityRenderer<MicroJetEntity>
{
    public MicroJetRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(MicroJetEntity entity)
    {
        return null;
    }

    @Override
    public void render(MicroJetEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light)
    {
        if(entity.getProjectile().isVisible() || entity.tickCount <= 1)
        {
            return;
        }

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180F));
        poseStack.mulPose(Axis.YP.rotationDegrees(entityYaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot() - 90));
        Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItem(), ItemDisplayContext.NONE, 15728880, OverlayTexture.NO_OVERLAY, poseStack, renderTypeBuffer, entity.level(), 0);
        poseStack.translate(0, -1, 0);
        RenderUtil.renderModel(SpecialModels.FLAME.getModel(), entity.getItem(), poseStack, renderTypeBuffer, 15728880, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
