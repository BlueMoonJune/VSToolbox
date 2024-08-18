package org.valkyrienskies.toolbox.content.block.mechanical.hinge

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import org.valkyrienskies.toolbox.content.block.ModBlockEntities

class HingeBlockEntity(pos: BlockPos, state: BlockState)
    : HingeConstraintBlockEntity(ModBlockEntities.HINGE.get(), pos, state)
{

}