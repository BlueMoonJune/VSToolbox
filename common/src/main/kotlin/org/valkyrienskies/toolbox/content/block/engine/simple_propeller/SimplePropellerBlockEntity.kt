package org.valkyrienskies.toolbox.content.block.engine.simple_propeller

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import org.valkyrienskies.core.api.ships.LoadedServerShip
import org.valkyrienskies.toolbox.content.block.ModBlockEntities
import org.valkyrienskies.toolbox.content.ship.modules.thruster.ThrusterControlModule
import org.valkyrienskies.mod.common.getShipObjectManagingPos

class SimplePropellerBlockEntity(pos: BlockPos, state: BlockState)
    : BlockEntity(ModBlockEntities.SIMPLE_PROPELLER.get(), pos, state)
{
        var rotation:Double = 0.0
}
