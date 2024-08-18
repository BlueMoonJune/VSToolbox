package org.valkyrienskies.toolbox.content.block

import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import org.valkyrienskies.toolbox.Toolbox
import org.valkyrienskies.toolbox.content.block.control.driver_seat.DriverSeatBlock
import org.valkyrienskies.toolbox.content.block.engine.blast_propeller.BlastPropellerBlock
import org.valkyrienskies.toolbox.content.block.engine.firework_thruster.FireworkThrusterBlock
import org.valkyrienskies.toolbox.content.block.engine.simple_propeller.SimplePropellerBlock
import org.valkyrienskies.toolbox.content.block.mechanical.hinge.HingeBlock
import org.valkyrienskies.toolbox.content.block.mechanical.rotator.RotatorBlock
import org.valkyrienskies.toolbox.registry.DeferredRegister

@Suppress("unused")
object ModBlocks {
    internal val BLOCKS = DeferredRegister.create(Toolbox.MOD_ID, Registries.BLOCK)

    val CONTROL_PANEL = BLOCKS.register("control_panel", ::DriverSeatBlock)

    val SIMPLE_PROPELLER = BLOCKS.register("simple_propeller", ::SimplePropellerBlock)
    val BLAST_PROPELLER = BLOCKS.register("blast_propeller", ::BlastPropellerBlock)
    val FIREWORK_THRUSTER = BLOCKS.register("firework_thruster", ::FireworkThrusterBlock)

    val HINGE = BLOCKS.register("hinge", ::HingeBlock)
    val ROTATOR = BLOCKS.register("rotator", ::RotatorBlock)

    fun register() {
        BLOCKS.applyAll()
    }

    // Blocks should also be registered as items, if you want them to be able to be held
    // aka all blocks
    fun registerItems(items: DeferredRegister<Item>) {
        BLOCKS.forEach {
            if (it !is NoBlockItem) {
                items.register(it.name) { BlockItem(it.get(), Item.Properties()) }
            }
        }
    }
}
