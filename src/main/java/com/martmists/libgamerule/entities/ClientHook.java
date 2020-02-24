package com.martmists.libgamerule.entities;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

public class ClientHook {
    public static MinecraftServer getClientServer(){
        return ((ServerGetter) FabricLoader.getInstance().getGameInstance()).getServer();
    }
}
