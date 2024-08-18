package org.valkyrienskies.toolbox.registry

import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import org.valkyrienskies.toolbox.content.block.ModBlocks
import org.valkyrienskies.toolbox.content.item.ModItems

object CreativeTabs {
    fun create(): CreativeModeTab {
        return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.toolbox"))
            .icon { ItemStack(ModBlocks.CONTROL_PANEL.get().asItem()) }
            .displayItems { _, output ->
                ModItems.ITEMS.forEach {
                    output.accept(it.get())
                }
            }
            .build()
    }
}
