package com.martmists.libgamerule.entities;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;

public class ClientHook {
    public static MinecraftServer getClientServer(){
        return ((MinecraftClient) FabricLoader.getInstance().getGameInstance()).getServer();
    }
}
