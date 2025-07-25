package top.andro.a_warfare.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.andro.a_warfare.block.MaceratorBlock;
import top.andro.a_warfare.blockentity.MaceratorBlockEntity;
import top.andro.a_warfare.client.SpecialModels;
import top.andro.a_warfare.client.util.RenderUtil;
import com.mojang.math.Axis;

@OnlyIn(Dist.CLIENT)
public class MaceratorRenderer implements BlockEntityRenderer<MaceratorBlockEntity> {

    public MaceratorRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(MaceratorBlockEntity macerator, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        BlockState blockState = macerator.getBlockState();
        float rotation = macerator.getWheelRotation(partialTicks);

        if (blockState.getValue(MaceratorBlock.LIT)) {
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_1.getModel(), rotation, 0.1875, 0.5, 0.1875);
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_2.getModel(), rotation, 0.8125, 0.5, 0.1875);
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_3.getModel(), rotation, 0.8125, 0.5, 0.8125);
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_4.getModel(), rotation, 0.1875, 0.5, 0.8125);
        } else {
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_1.getModel(), 0, 0.1875, 0.5, 0.1875);
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_2.getModel(), 0, 0.8125, 0.5, 0.1875);
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_3.getModel(), 0, 0.8125, 0.5, 0.8125);
            renderWheel(matrixStack, buffer, light, overlay, SpecialModels.MACERATOR_WHEEL_4.getModel(), 0, 0.1875, 0.5, 0.8125);
        }
    }

    private void renderWheel(PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay, BakedModel model, float rotation, double x, double y, double z) {
        if (model != null) {
            matrixStack.pushPose();
            matrixStack.translate(x, y, z);
            matrixStack.mulPose(Axis.YP.rotationDegrees(rotation));
            matrixStack.translate(-x, -y, -z);
            RenderUtil.renderMaceratorWheel(model, matrixStack, buffer, light, overlay);
            matrixStack.popPose();
        }
    }
}