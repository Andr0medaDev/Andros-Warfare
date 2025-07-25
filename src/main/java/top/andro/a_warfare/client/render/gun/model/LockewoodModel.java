package top.andro.a_warfare.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
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
public class LockewoodModel implements IOverrideModel {

    @SuppressWarnings("resource")
    @Override
    public void render(float partialTicks, ItemDisplayContext transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {

        // Renders the static parts of the model.
        RenderUtil.renderModel(SpecialModels.LOCKEWOOD_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);

        // Renders the iron sights if no scope is attached.
        if (Gun.getScope(stack) == null) {
            RenderUtil.renderModel(SpecialModels.LOCKEWOOD_SIGHTS.getModel(), stack, matrixStack, buffer, light, overlay);
        } else {
            RenderUtil.renderModel(SpecialModels.LOCKEWOOD_NO_SIGHTS.getModel(), stack, matrixStack, buffer, light, overlay);
        }

        // Render stock attachments
        renderStockAttachments(stack, matrixStack, buffer, light, overlay);

        // Render magazine attachments
        renderMagazineAttachments(stack, matrixStack, buffer, light, overlay);

        // Render barrel and attachments with the new system
        renderBarrelAndAttachments(stack, matrixStack, buffer, light, overlay);

        // Render the moving parts and attachments (if applicable)
        if (entity.equals(Minecraft.getInstance().player)) {
            matrixStack.pushPose();
            matrixStack.translate(0, -5.8 * 0.0625, 0);
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown = (float) ease(cooldown);

            matrixStack.translate(0, 0, cooldown / 8);
            matrixStack.translate(0, 5.8 * 0.0625, 0);

            RenderUtil.renderModel(SpecialModels.LOCKEWOOD_BOLT.getModel(), stack, matrixStack, buffer, light, overlay);
            matrixStack.popPose();
        }

        // Render non-animated underbarrel attachments (bayonets)
        renderUnderBarrelAttachments(stack, matrixStack, buffer, light, overlay);
    }

    private void renderBarrelAndAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        boolean hasExtendedBarrel = false;

        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.BARREL)) {
            if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.EXTENDED_BARREL.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_EXT_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
                hasExtendedBarrel = true;
            } else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.SILENCER.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_SILENCER.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.ADVANCED_SILENCER.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_ADVANCED_SILENCER.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.MUZZLE_BRAKE.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_MUZZLE_BRAKE.getModel(), stack, matrixStack, buffer, light, overlay);
            }
        }

        // Render the standard barrel if no extended barrel is attached
        if (!hasExtendedBarrel) {
            RenderUtil.renderModel(SpecialModels.LOCKEWOOD_STAN_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
        }
    }

    private void renderStockAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.STOCK)) {
            if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WOODEN_STOCK.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_STOCK_WOODEN.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.LIGHT_STOCK.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_STOCK_LIGHT.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WEIGHTED_STOCK.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_STOCK_HEAVY.getModel(), stack, matrixStack, buffer, light, overlay);
            }
        }
    }

    private void renderMagazineAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.MAGAZINE)) {
            if (Gun.getAttachment(IAttachment.Type.MAGAZINE, stack).getItem() == ModItems.EXTENDED_MAG.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_EXTENDED_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.MAGAZINE, stack).getItem() == ModItems.SPEED_MAG.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_SPEED_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.MAGAZINE, stack).getItem() == ModItems.PLUS_P_MAG.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_EXTENDED_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
            }
        } else {
            RenderUtil.renderModel(SpecialModels.LOCKEWOOD_STANDARD_MAG.getModel(), stack, matrixStack, buffer, light, overlay);
        }
    }

    private void renderUnderBarrelAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.UNDER_BARREL)) {
            if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.VERTICAL_GRIP.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_GRIP_VERTICAL.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.LIGHT_GRIP.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_GRIP_LIGHT.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.IRON_BAYONET.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_IRON_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.ANTHRALITE_BAYONET.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_ANTHRALITE_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.DIAMOND_BAYONET.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_DIAMOND_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            } else if (Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() == ModItems.NETHERITE_BAYONET.get()) {
                RenderUtil.renderModel(SpecialModels.LOCKEWOOD_NETHERITE_BAYONET.getModel(), stack, matrixStack, buffer, light, overlay);
            }
        }
    }

    private double ease(double x) {
        return 1 - Math.pow(1 - (2 * x), 4);
    }

}
