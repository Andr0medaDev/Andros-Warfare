package top.andro.a_warfare.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.client.SpecialModels;
import top.andro.a_warfare.client.render.gun.IOverrideModel;
import top.andro.a_warfare.client.util.RenderUtil;
import top.andro.a_warfare.common.Gun;
import top.andro.a_warfare.init.ModItems;
import top.andro.a_warfare.item.attachment.IAttachment;

/**
 * Since we want to have an animation for the charging handle, we will be overriding the standard model rendering.
 * This also allows us to replace the model for the different stocks.
 */
public class UmaxPistolModel implements IOverrideModel {

    @SuppressWarnings("resource")
    @Override
    public void render(float partialTicks, ItemDisplayContext transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {

        // Renders the static parts of the model.
        RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);

        // Renders the iron sights if no scope is attached.
        if (Gun.getScope(stack) == null) {
            RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_SIGHTS.getModel(), stack, matrixStack, buffer, light, overlay);
        } else {
            RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_NO_SIGHTS.getModel(), stack, matrixStack, buffer, light, overlay);
        }
        // Render stock attachments
        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.STOCK)) {
            if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WOODEN_STOCK.get()) {
                RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_STOCK_WOODEN.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.LIGHT_STOCK.get()) {
                RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_STOCK_LIGHT.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WEIGHTED_STOCK.get()) {
                RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_STOCK_HEAVY.getModel(), stack, matrixStack, buffer, light, overlay);
            }
        } else {
            RenderUtil.renderModel(SpecialModels.UMAX_PISTOL_STANDARD_GRIP.getModel(), stack, matrixStack, buffer, light, overlay);
        }

    }
}
