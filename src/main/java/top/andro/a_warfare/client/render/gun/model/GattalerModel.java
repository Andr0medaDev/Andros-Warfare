package top.andro.a_warfare.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.andro.a_warfare.client.SpecialModels;
import top.andro.a_warfare.client.render.gun.IOverrideModel;
import top.andro.a_warfare.client.util.RenderUtil;

public class GattalerModel implements IOverrideModel {
    private float barrelRotation = 0.0f;

    @Override
    public void render(float partialTicks, ItemDisplayContext transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        RenderUtil.renderModel(SpecialModels.GATTALER_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
        if (entity.equals(Minecraft.getInstance().player)) {
            renderBarrels(matrixStack, buffer, stack, light, overlay);
        }
    }

    private void renderBarrels(PoseStack matrixStack, MultiBufferSource buffer, ItemStack stack, int light, int overlay) {
        assert Minecraft.getInstance().player != null;
        ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
        float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
        matrixStack.pushPose();
        matrixStack.translate(0, -0.0, 0);
        if (cooldown > 0) {
            barrelRotation += (float) (5.0f * (1.0 - cooldown));
            barrelRotation = barrelRotation % 360;
        }
        matrixStack.mulPose(Axis.ZP.rotationDegrees(barrelRotation));
        matrixStack.translate(0, 0.0, 0);
        RenderUtil.renderModel(SpecialModels.GATTALER_BARREL.getModel(), stack, matrixStack, buffer, light, overlay);
        matrixStack.popPose();
    }
}
