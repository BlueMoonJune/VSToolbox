package org.valkyrienskies.toolbox.content.ship

import org.valkyrienskies.core.impl.game.ships.PhysShipImpl

interface IShipControlModule {
    val shipControl: ToolboxShipControl

    fun onTick()
    fun onPhysTick(physShip:PhysShipImpl)

}