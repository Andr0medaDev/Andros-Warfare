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
public class FreyrModel implements IOverrideModel {

    @SuppressWarnings("resource")
    @Override
    public void render(float partialTicks, ItemDisplayContext transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        RenderUtil.renderModel(SpecialModels.FREYR_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);

        if ((Gun.getScope(stack) == null))
            RenderUtil.renderModel(SpecialModels.FREYR_SIGHTS.getModel(), stack, matrixStack, buffer, light, overlay);
        else
            RenderUtil.renderModel(SpecialModels.FREYR_NO_SIGHTS.getModel(), stack, matrixStack, buffer, light, overlay);

        renderStockAttachments(stack, matrixStack, buffer, light, overlay);
        renderUnderBarrelAttachments(stack, matrixStack, buffer, light, overlay);
        renderMagazineAttachments(stack, matrixStack, buffer, light, overlay);
        renderBarrelAndAttachments(stack, matrixStack, buffer, light, overlay);
    }

    private void renderStockAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if ((Gun.hasAttachmentEquipped(stack, IAttachment.Type.STOCK))) {
            if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WOODEN_STOCK.get())
                RenderUtil.renderModel(SpecialModels.FREYR_STOCK_WOODEN.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.LIGHT_STOCK.get())
                RenderUtil.renderModel(SpecialModels.FREYR_STOCK_LIGHT.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WEIGHTED_STOCK.get())
                RenderUtil.renderModel(SpecialModels.FREYR_STOCK_HEAVY.getModel(), stack, matrixStack, buffer, light, overlay);
        }
        else
            RenderUtil.renderModel(SpecialModels.FREYR_STANDARD_GRIP.getModel(), stack, matrixStack, buffer, light, overlay);
    }

    private void renderUnderBarrelAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.UNDER_BARREL)) {
            if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.VERTICAL_GRIP.get())
                RenderUtil.renderModel(SpecialModels.FREYR_GRIP_VERTICAL.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.LIGHT_GRIP.get())
                RenderUtil.renderModel(SpecialModels.FREYR_GRIP_LIGHT.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.IRON_BAYONET.get())
                RenderUtil.renderModel(SpecialModels.FREYR_IRON_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.ANTHRALITE_BAYONET.get())
                RenderUtil.renderModel(SpecialModels.FREYR_ANTHRALITE_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.DIAMOND_BAYONET.get())
                RenderUtil.renderModel(SpecialModels.FREYR_DIAMOND_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.NETHERITE_BAYONET.get())
                RenderUtil.renderModel(SpecialModels.FREYR_NETHERITE_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
        }
    }

    private void renderMagazineAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if ((Gun.hasAttachmentEquipped(stack, IAttachment.Type.MAGAZINE))) {
            if (Gun.getAttachment(IAttachment.Type.MAGAZINE, stack).getItem() == ModItems.EXTENDED_MAG.get())
                RenderUtil.renderModel(SpecialModels.FREYR_EXTENDED_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
            if (Gun.getAttachment(IAttachment.Type.MAGAZINE, stack).getItem() == ModItems.PLUS_P_MAG.get())
                RenderUtil.renderModel(SpecialModels.FREYR_EXTENDED_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
            if (Gun.getAttachment(IAttachment.Type.MAGAZINE, stack).getItem() == ModItems.SPEED_MAG.get())
                RenderUtil.renderModel(SpecialModels.FREYR_SPEED_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
        }
        else
            RenderUtil.renderModel(SpecialModels.FREYR_STANDARD_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
    }

    private void renderBarrelAndAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        boolean hasExtendedBarrel = false;

        if ((Gun.hasAttachmentEquipped(stack, IAttachment.Type.BARREL))) {
            if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.EXTENDED_BARREL.get()) {
                RenderUtil.renderModel(SpecialModels.FREYR_EXT_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
                hasExtendedBarrel = true;
            } else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.SILENCER.get())
                RenderUtil.renderModel(SpecialModels.FREYR_SILENCER.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.ADVANCED_SILENCER.get())
                RenderUtil.renderModel(SpecialModels.FREYR_ADVANCED_SILENCER.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.MUZZLE_BRAKE.get())
                RenderUtil.renderModel(SpecialModels.FREYR_MUZZLE_BRAKE.getModel(), stack, matrixStack, buffer, light, overlay);
        }

        // Render the standard barrel if no extended barrel is attached
        if (!hasExtendedBarrel) {
            RenderUtil.renderModel(SpecialModels.FREYR_STAN_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
        }
    }
}