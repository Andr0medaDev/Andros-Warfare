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
public class BruiserModel implements IOverrideModel {

    @SuppressWarnings("resource")
    @Override
    public void render(float partialTicks, ItemDisplayContext transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {

        // Renders the static parts of the model.
        RenderUtil.renderModel(SpecialModels.BRUISER_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);

        if (entity.equals(Minecraft.getInstance().player)) {
            // Always push.
            matrixStack.pushPose();
            // Don't touch this, it's better to use the display options in Blockbench.
            matrixStack.translate(0, -5.8 * 0.0625, 0);
            // Gets the cooldown tracker for the item. Items like swords and enderpearls also have this.
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown = (float) ease(cooldown);
            // Apply transformations to the barrel and attached components
            matrixStack.translate(0, 0, cooldown / 6);
            matrixStack.translate(0, 5.8 * 0.0625, 0);
            // Render the barrel and its attachments
            renderBarrelAndAttachments(stack, matrixStack, buffer, light, overlay);
            // Always pop
            matrixStack.popPose();
        }
    }

    private void renderBarrelAndAttachments(ItemStack stack, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        // Check if extended barrel is attached
        boolean hasExtendedBarrel = Gun.hasAttachmentEquipped(stack, IAttachment.Type.BARREL) &&
                Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.EXTENDED_BARREL.get();

        // Render the appropriate barrel
        if (hasExtendedBarrel) {
            RenderUtil.renderModel(SpecialModels.BRUISER_EXT_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
        } else {
            RenderUtil.renderModel(SpecialModels.BRUISER_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
        }

        // Render other barrel attachments
        if (Gun.hasAttachmentEquipped(stack, IAttachment.Type.BARREL)) {
            if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.SILENCER.get())
                RenderUtil.renderModel(SpecialModels.BRUISER_SILENCER.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.MUZZLE_BRAKE.get())
                RenderUtil.renderModel(SpecialModels.BRUISER_MUZZLE_BRAKE.getModel(), stack, matrixStack, buffer, light, overlay);
            else if (Gun.getAttachment(IAttachment.Type.BARREL, stack).getItem() == ModItems.ADVANCED_SILENCER.get())
                RenderUtil.renderModel(SpecialModels.BRUISER_ADVANCED_SILENCER.getModel(), stack, matrixStack, buffer, light, overlay);
        }
    }

    private double ease(double x) {
        return 1 - Math.pow(1 - (2 * x), 4);
    }
}