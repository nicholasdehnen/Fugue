package com.cleanroommc.fugue.mixin.tombstone;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ovh.corail.tombstone.helper.Helper;

@Mixin(value = Helper.class, remap = false)
public class HelperMixin {
    // Fixes java.lang.NoSuchMethodError in Corail Tombstone 4.1.2
    @Inject(method = "loadContributors", at = @At("HEAD"), cancellable = true)
    private static void onLoadContributors(MinecraftServer server, CallbackInfo ci) {
        // Simply cancel the method execution, preventing the Futures.addCallback call to fail
        // Contributors link is dead anyways
        ci.cancel();
    }
}
