package org.valkyrienskies.toolbox.content.item

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import org.valkyrienskies.toolbox.Toolbox
import org.valkyrienskies.toolbox.content.block.ModBlocks
import org.valkyrienskies.toolbox.registry.DeferredRegister

@Suppress("unused")
object ModItems {
    internal val ITEMS = DeferredRegister.create(Toolbox.MOD_ID, Registries.ITEM)
    val TAB: ResourceKey<CreativeModeTab> =
        ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation(Toolbox.MOD_ID, "toolbox_tab"))

//    val HAMMER = ITEMS.register("hammer", ::GyroRotator)

    fun register() {
        ModBlocks.registerItems(ITEMS)
        ITEMS.applyAll()
    }

    private infix fun Item.byName(name: String) = ITEMS.register(name) { this }
}
