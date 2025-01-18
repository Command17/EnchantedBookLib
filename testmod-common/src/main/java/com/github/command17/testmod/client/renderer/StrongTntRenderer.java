package com.github.command17.testmod.client.renderer;

import com.github.command17.testmod.entity.StrongTntEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.PrimedTnt;
import org.jetbrains.annotations.NotNull;

public class StrongTntRenderer extends EntityRenderer<StrongTntEntity, TntRenderState> {
    private final BlockRenderDispatcher blockRenderer;

    public StrongTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(TntRenderState renderState, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0, 0.5f, 0);

        float f = renderState.fuseRemainingInTicks;

        if (renderState.fuseRemainingInTicks < 10) {
            float g = 1 - renderState.fuseRemainingInTicks / 10;
            g = Mth.clamp(g, 0, 1);
            g *= g;
            g *= g;
            float h = 1 + g * 0.3f;
            poseStack.scale(h, h, h);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
        poseStack.translate(-0.5f, -0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));

        if (renderState.blockState != null) {
            TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, renderState.blockState, poseStack, buffer, packedLight, (int)f / 5 % 2 == 0);
        }

        poseStack.popPose();
        super.render(renderState, poseStack, buffer, packedLight);
    }

    @NotNull
    @Override
    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void extractRenderState(StrongTntEntity entity, TntRenderState renderState, float partialTick) {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.fuseRemainingInTicks = (float) entity.getFuse() - partialTick + 1;
        renderState.blockState = entity.getBlockState();
    }
}
