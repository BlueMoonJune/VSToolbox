package org.valkyrienskies.toolbox.api.extension

import com.mojang.blaze3d.vertex.PoseStack

fun PoseStack.pose(block: PoseStack.() -> Unit) {
    pushPose()
    block()
    popPose()
}