package org.valkyrienskies.toolbox.forge

import net.minecraftforge.client.event.ModelEvent
import org.valkyrienskies.toolbox.Toolbox
import thedarkcolour.kotlinforforge.forge.MOD_BUS

object ToolboxModForgeClient {
    private var happendClientSetup = false

    fun registerClient() {
        MOD_BUS.addListener { event: ModelEvent.BakingCompleted ->
            clientSetup( event )
        }
    }

    fun clientSetup(event: ModelEvent.BakingCompleted) {
        if (happendClientSetup) { return }
        happendClientSetup = true
        Toolbox.initClient()
    }
}