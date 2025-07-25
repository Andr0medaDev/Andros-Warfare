package top.andro.a_warfare.client.render.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.andro.a_warfare.client.handler.GunRenderingHandler;
import top.andro.a_warfare.client.render.IHeldAnimation;
import top.andro.a_warfare.client.util.RenderUtil;
import top.andro.a_warfare.item.animated.AnimatedGunItem;

/**
 * Author: MrCrayfish
 */
public class OneHandedPose implements IHeldAnimation {

    @Override
    @OnlyIn(Dist.CLIENT)
    public void applyPlayerModelRotation(Player player, ModelPart rightArm, ModelPart leftArm, ModelPart head, InteractionHand hand, float aimProgress) {
        boolean right = Minecraft.getInstance().options.mainHand().get() == HumanoidArm.RIGHT ? hand == InteractionHand.MAIN_HAND : hand == InteractionHand.OFF_HAND;
        ModelPart arm = right ? rightArm : leftArm;

        GunRenderingHandler renderingHandler = GunRenderingHandler.get();
        if (renderingHandler.isThirdPersonMeleeAttacking()) {
            applySimplifiedMeleePose(player, arm, renderingHandler.getThirdPersonMeleeProgress());
        } else {
            IHeldAnimation.copyModelAngles(head, arm);
            arm.xRot += (float) Math.toRadians(-90F);

            if (player.getUseItem().getItem() == Items.SHIELD) {
                arm.xRot = (float) Math.toRadians(-30F);
            }
        }
    }

    private void applySimplifiedMeleePose(Player player, ModelPart arm, float progress) {
        if (progress < 0.2f) {
            // Initial lower
            float lowerProgress = progress / 0.2f;
            arm.xRot = (float) Math.toRadians(-70F - 10F * lowerProgress);
            arm.yRot = 0F;
        } else if (progress < 0.7f) {
            // Swing up
            float swingProgress = (progress - 0.2f) / 0.5f;
            arm.xRot = (float) Math.toRadians(-80F + 80F * swingProgress);
            arm.yRot = 0F;
        } else {
            // Return to idle
            float returnProgress = (progress - 0.7f) / 0.3f;
            arm.xRot = (float) Math.toRadians(0F - 70F * returnProgress);
            arm.yRot = 0F;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderFirstPersonArms(Player player, HumanoidArm hand, ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int light, float partialTicks) {
        if (stack.getItem() instanceof AnimatedGunItem) {
            return;
        }
        poseStack.mulPose(Axis.YP.rotationDegrees(180F));

        BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, player.level(), player, 0);
        float translateX = model.getTransforms().firstPersonRightHand.translation.x();
        float translateZ = model.getTransforms().firstPersonRightHand.translation.z();
        int side = hand.getOpposite() == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate(translateX * side, 0, -translateZ -0.1F);

        boolean slim = Minecraft.getInstance().player.getModelName().equals("slim");
        float armWidth = slim ? 3.0F : 4.0F;

        poseStack.scale(0.55F, 0.55F, 0.55F);
        poseStack.translate(-4.0 * 0.0625 * side, 0, 0);
        poseStack.translate(-(armWidth / 2.0) * 0.0625 * side, 0, 0.1);

        poseStack.translate(0, 0.15, -1.3125);
        poseStack.mulPose(Axis.XP.rotationDegrees(75F));

        RenderUtil.renderFirstPersonArm((LocalPlayer) player, hand, poseStack, buffer, light);
    }

    @Override
    public boolean applyOffhandTransforms(Player player, PlayerModel model, ItemStack stack, PoseStack poseStack, float partialTicks) {
        poseStack.mulPose(Axis.YP.rotationDegrees(180F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));

        if (player.isCrouching()) {
            poseStack.translate(-4.5 * 0.0625, -15 * 0.0625, -4 * 0.0625);
        } else if (!player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            poseStack.translate(-4.0 * 0.0625, -13 * 0.0625, 1 * 0.0625);
        } else {
            poseStack.translate(-3.5 * 0.0625, -13 * 0.0625, 1 * 0.0625);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(90F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(75F));
        poseStack.mulPose(Axis.ZP.rotationDegrees((float) (Math.toDegrees(model.rightLeg.xRot) / 10F)));
        poseStack.scale(0.5F, 0.5F, 0.5F);

        return true;
    }

    @Override
    public boolean canApplySprintingAnimation() {
        return false;
    }

    @Override
    public boolean canRenderOffhandItem() {
        return true;
    }

    @Override
    public double getFallSwayZOffset() {
        return 0.5;
    }
}
