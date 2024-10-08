package org.valkyrienskies.toolbox.content.ship.modules.thruster

import org.valkyrienskies.toolbox.ModConfig
import org.valkyrienskies.toolbox.ModConfig.SERVER

object ThrusterSpecs {

    fun getMaxThrustForThruster(type:ThrusterType): Double {
        when (type) {
            ThrusterType.SIMPLE_PROPELLER -> return SERVER.SIMPLE_PROPELLER_FORCE
            ThrusterType.BLAST_PROPELLER -> return SERVER.BLAST_PROPELLER_FORCE
            ThrusterType.FIREWORK_THRUSTER -> return SERVER.FIREWORK_THRUSTER_FORCE
        }
        return SERVER.SIMPLE_PROPELLER_FORCE
    }

}

enum class ThrusterType {
    SIMPLE_PROPELLER,
    BLAST_PROPELLER,
    FIREWORK_THRUSTER
}