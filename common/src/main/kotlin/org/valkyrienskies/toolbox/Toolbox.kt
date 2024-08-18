package org.valkyrienskies.toolbox

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import org.valkyrienskies.core.impl.config.VSConfigClass
import org.valkyrienskies.toolbox.content.block.ModBlockEntities
import org.valkyrienskies.toolbox.content.block.ModBlocks
import org.valkyrienskies.toolbox.content.entity.ModEntities
import org.valkyrienskies.toolbox.content.item.ModItems
import org.valkyrienskies.toolbox.content.gui.ModClientScreens
import org.valkyrienskies.toolbox.content.gui.ModScreens

object Toolbox {
    const val MOD_ID = "toolbox"

    @JvmStatic
    fun init() {
        ModBlocks.register()
        ModBlockEntities.register()
        ModItems.register()
        ModScreens.register()
        ModEntities.register()

        VSConfigClass.registerConfig("toolbox", ModConfig::class.java)
    }

    @JvmStatic
    fun initClient() {
        ModClientScreens.register()
        ModEntities.registerRenderers()
    }


    interface ClientRenderers {
        fun <T: BlockEntity> registerBlockEntityRenderer(t: BlockEntityType<T>, r: BlockEntityRendererProvider<T>)
    }
    @JvmStatic
    fun initClientRenderers(clientRenderers: ClientRenderers) {
        ModBlockEntities.initClientRenderers(clientRenderers)
    }
}
