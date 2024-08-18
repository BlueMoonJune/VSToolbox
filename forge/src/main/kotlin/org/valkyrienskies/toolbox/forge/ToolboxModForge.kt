package org.valkyrienskies.toolbox.forge

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.client.ConfigScreenHandler
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.client.event.ModelEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import org.valkyrienskies.core.impl.config.VSConfigClass.Companion.getRegisteredConfig
import org.valkyrienskies.toolbox.ModConfig
import org.valkyrienskies.toolbox.Toolbox
import org.valkyrienskies.toolbox.Toolbox.init
import org.valkyrienskies.toolbox.Toolbox.initClientRenderers
import org.valkyrienskies.toolbox.content.render.ModModels
import org.valkyrienskies.toolbox.registry.CreativeTabs
import org.valkyrienskies.mod.compat.clothconfig.VSClothConfig.createConfigScreenFor
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(Toolbox.MOD_ID)
class ToolboxModForge {
    init {
        runForDist (
            clientTarget = {
                ToolboxModForgeClient.registerClient()
            },
            serverTarget = {}
        )
        LOADING_CONTEXT.registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory::class.java
        ) {
            ConfigScreenHandler.ConfigScreenFactory { _: Minecraft?, parent: Screen? ->
                createConfigScreenFor(
                    parent!!,
                    getRegisteredConfig(ModConfig::class.java)
                )
            }
        }

        MOD_BUS.addListener { event: ModelEvent.RegisterAdditional ->
            println("${Toolbox.MOD_ID}: Registering models")
            ModModels.MODELS.forEach { rl ->
                println("${Toolbox.MOD_ID}: Registering model $rl")
                event.register(rl)
            }
        }

        MOD_BUS.addListener { event: EntityRenderersEvent.RegisterRenderers ->
            entityRenderers(
                event
            )
        }

        init()

        val deferredRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Toolbox.MOD_ID)
        deferredRegister.register("general") {
            CreativeTabs.create()
        }
        deferredRegister.register(getModBus())
    }

    private fun entityRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        initClientRenderers(
            object : Toolbox.ClientRenderers {
                override fun <T : BlockEntity> registerBlockEntityRenderer(
                    t: BlockEntityType<T>,
                    r: BlockEntityRendererProvider<T>
                ) = event.registerBlockEntityRenderer(t, r)
            }
        )
    }

    companion object {
        fun getModBus(): IEventBus = MOD_BUS
    }
}
