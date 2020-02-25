package com.martmists.libgamerule;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class GameruleClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Gamerule.SERVER_SUPPLIER = () -> MinecraftClient.getInstance().getServer();
    }
}
