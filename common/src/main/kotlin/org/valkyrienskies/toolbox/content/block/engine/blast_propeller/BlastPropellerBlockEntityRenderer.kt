package org.valkyrienskies.toolbox.content.block.engine.blast_propeller

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.joml.AxisAngle4f
import org.joml.Quaternionf
import org.joml.Vector3f
import org.valkyrienskies.toolbox.ModConfig
import org.valkyrienskies.toolbox.api.extension.pose
import org.valkyrienskies.toolbox.content.render.ModModels

class BlastPropellerBlockEntityRenderer (
    val model: ModModels.Model
): BlockEntityRenderer<BlastPropellerBlockEntity> {

    override fun render(
        be: BlastPropellerBlockEntity,
        partial: Float,
        pose: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        pose.pose {
            translate(0.5, 0.5, 0.5)
            mulPose(be.blockState.getValue(DirectionalBlock.FACING).rotation)
            be.rotation += be.blockState.getValue(BlockStateProperties.POWER).toDouble() * ModConfig.CLIENT.BLAST_PROPELLER_ROT_SPEED * (Minecraft.getInstance().deltaFrameTime)
            if(be.rotation >= 360.0) be.rotation -= 360.0
            pose.mulPose(Quaternionf(AxisAngle4f(Math.toRadians( be.rotation ).toFloat(), 0f, 1f, 0f)))
            translate(-0.5, 0.0, -0.5)
            model.renderer.render(
                pose,
                be,
                bufferSource,
                packedLight,
                packedOverlay
            )
        }
    }

}