package top.andro.a_warfare.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;

public class MiniButton extends Button
{
    private final int u, v;
    private final ResourceLocation texture;

    public MiniButton(int x, int y, int u, int v, ResourceLocation texture, OnPress onPress)
    {
        super(x, y, 10, 10, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);
        this.u = u;
        this.v = v;
        this.texture = texture;
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        pGuiGraphics.blit(texture, this.getX(), this.getY(), this.u, this.v, this.width, this.height);
        if(this.isHovered)
        {
            pGuiGraphics.fillGradient(this.getX(), this.getY(), this.getX() + 10, this.getY() + 10, -2130706433, -2130706433);
        }
    }
}
