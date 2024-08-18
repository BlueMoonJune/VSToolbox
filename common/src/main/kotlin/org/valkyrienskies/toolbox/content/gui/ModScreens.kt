package org.valkyrienskies.toolbox.content.gui

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import org.valkyrienskies.toolbox.Toolbox
import org.valkyrienskies.toolbox.registry.DeferredRegister

private typealias HFactory<T> = (syncId: Int, playerInv: Inventory) -> T

@Suppress("unused")
object ModScreens {
    private val SCREENS = DeferredRegister.create(Toolbox.MOD_ID, Registries.MENU)

//    val SHIP_HELM = ShipHelmScreenMenu.factory withName "ship_helm"
//    val ENGINE = EngineScreenMenu.factory withName "engine"

    fun register() {
        SCREENS.applyAll()
    }

    private infix fun <T : AbstractContainerMenu> HFactory<T>.withName(name: String) =
        SCREENS.register(name) { MenuType(this, FeatureFlags.VANILLA_SET) }
}
