package org.valkyrienskies.toolbox.fabric.services;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;
import org.valkyrienskies.toolbox.fabric.DeferredRegisterImpl;
import org.valkyrienskies.toolbox.registry.DeferredRegister;
import org.valkyrienskies.toolbox.api.services.DeferredRegisterBackend;

public class DeferredRegisterBackendFabric implements DeferredRegisterBackend {

    @NotNull
    @Override
    public <T> DeferredRegister<T> makeDeferredRegister(
            @NotNull final String id,
            @NotNull final ResourceKey<Registry<T>> registry) {
        return new DeferredRegisterImpl<>(id, registry);
    }
}
