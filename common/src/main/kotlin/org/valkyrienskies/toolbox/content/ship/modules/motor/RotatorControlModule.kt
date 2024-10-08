package org.valkyrienskies.toolbox.content.ship.modules.motor

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING
import org.joml.Vector3d
import org.valkyrienskies.toolbox.api.util.InterpolationCurve
import org.valkyrienskies.core.api.ships.ServerShip
import org.valkyrienskies.core.api.ships.getAttachment
import org.valkyrienskies.core.api.ships.saveAttachment
import org.valkyrienskies.core.impl.game.ships.PhysShipImpl
import org.valkyrienskies.toolbox.content.ship.ToolboxShipControl
import org.valkyrienskies.toolbox.content.ship.IShipControlModule
import org.valkyrienskies.mod.common.util.toJOMLD

class RotatorControlModule(override val shipControl: ToolboxShipControl) : IShipControlModule {

    private val spinners = HashMap<BlockPos, Pair<BlockState, Boolean>>()

    fun addSpinner(pos: BlockPos, state: BlockState, isInverted: Boolean) {
        spinners.put(pos, Pair(state, isInverted))
    }
    fun removeSpinner(pos: BlockPos) {
        spinners.remove(pos)
    }

    override fun onPhysTick(physShip: PhysShipImpl) {
        val mass = physShip.inertia.shipMass
        val omega = physShip.poseVel.omega
        val vel = physShip.poseVel.vel

        spinners.forEach {
            val torque = it.value.first.getValue(FACING).normal.toJOMLD().mul(it.value.first.getValue(BlockStateProperties.POWER).toDouble(), Vector3d())

            val torqueGlobal = physShip.transform.shipToWorldRotation.transform(torque, Vector3d())

            val rotationAxis = torqueGlobal.normalize(Vector3d())

            val inversionMultiplier = if(it.value.second) 1.0 else -1.0

            val rpm = omega.mul(rotationAxis, Vector3d()).length() * 10
            val powerAtRpm = powerCurve.getValueAtX(rpm)

            if (!powerAtRpm.isNaN() && !inversionMultiplier.isNaN()){
                physShip.applyInvariantTorque(torqueGlobal.mul(powerAtRpm * inversionMultiplier)) // replace with curve
                println("RPM: $rpm torque:$powerAtRpm")
            }
        }
    }

    override fun onTick() { }

    companion object {
        fun getOrCreate(ship: ServerShip): RotatorControlModule {
            val control: ToolboxShipControl = ship.getAttachment<ToolboxShipControl>() ?: ToolboxShipControl().also { ship.saveAttachment(it) }
            control.loadedModules.forEach { if(it is RotatorControlModule) return it }
            val module = RotatorControlModule(control)
            control.loadedModules.add(module)
            return module
        }

        val powerCurve = InterpolationCurve()
            .createDataPoint(0.0, 30000.0)
            .createDataPoint(100.0, 30000.0)
            .createDataPoint(200.0, 28000.0)
            .createDataPoint(300.0, 25000.0)
            .createDataPoint(400.0, 15000.0)
            .createDataPoint(500.0, 10000.0)
            .createDataPoint(600.0, 8000.0)
            .createDataPoint(700.0, 6000.0)
            .createDataPoint(800.0, 5000.0)
            .createDataPoint(900.0, 2500.0)
            .createDataPoint(1000.0, 0.0)
    }
}