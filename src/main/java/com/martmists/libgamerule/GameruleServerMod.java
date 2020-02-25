package com.martmists.libgamerule;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

public class GameruleServerMod implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Gamerule.SERVER_SUPPLIER = () -> (MinecraftServer) FabricLoader.getInstance().getGameInstance();
    }
}
