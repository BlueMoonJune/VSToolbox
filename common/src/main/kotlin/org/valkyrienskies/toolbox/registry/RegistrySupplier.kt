package org.valkyrienskies.toolbox.registry

interface RegistrySupplier<T> {

    val name: String
    fun get(): T
}
