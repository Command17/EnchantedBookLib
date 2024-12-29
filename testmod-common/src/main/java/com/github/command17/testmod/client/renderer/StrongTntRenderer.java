package com.github.command17.testmod.client.renderer;

import com.github.command17.testmod.entity.StrongTntEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class StrongTntRenderer extends EntityRenderer<StrongTntEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public StrongTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    public void render(StrongTntEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0, 0.5f, 0);

        int i = entity.getFuse();

        if (i - partialTicks + 1 < 10) {
            float f = 1 - (i - partialTicks + 1) / 10;
            f = Mth.clamp(f, 0, 1);
            f *= f;
            f *= f;
            float g = 1 + f * 0.3f;
            poseStack.scale(g, g, g);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
        poseStack.translate(-0.5f, -0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, entity.getBlockState(), poseStack, buffer, packedLight, i / 5 % 2 == 0);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(StrongTntEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
