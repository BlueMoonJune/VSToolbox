package org.valkyrienskies.toolbox.content.block.mechanical.hinge

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import org.valkyrienskies.toolbox.content.block.NoBlockItem

class HingeHeadBlock : DirectionalBlock(Properties.of().sound(SoundType.STONE).strength(1.0f, 2.0f)), NoBlockItem