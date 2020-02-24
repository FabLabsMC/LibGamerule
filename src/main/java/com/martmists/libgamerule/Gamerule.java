package com.martmists.libgamerule;

import blue.endless.jankson.annotation.Nullable;
import com.martmists.libgamerule.entities.ClientHook;
import com.martmists.libgamerule.entities.ValueGetter;
import com.martmists.libgamerule.mixin.GameRulesAccessor;
import com.martmists.libgamerule.mixin.RuleTypeAccessor;
import com.mojang.brigadier.arguments.ArgumentType;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Gamerule {
    public static boolean dirty = false;

    public static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String name, GameRules.RuleType<T> type) {
        dirty = true;
        GameRules.RuleKey<T> key = GameRulesAccessor.invokeRegister(name, type);
        return key;
    }

    @Nullable
    public static <T extends GameRules.Rule<T> & ValueGetter<V>, V> V get(GameRules.RuleKey<T> key) {
        MinecraftServer server;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            server = ClientHook.getClientServer();  // ((MinecraftClient)FabricLoader.getInstance().getGameInstance()).getServer()
        } else {
            server = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
        }
        if (server == null) {
            return null;
        }
        return server.getGameRules().get(key).get();
    }

    public static <T extends GameRules.Rule<T>> GameRules.RuleType<T> createRuleType(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> factory) {
        return createRuleType(argumentType, factory, (s, r) -> {
        });
    }

    public static <T extends GameRules.Rule<T>> GameRules.RuleType<T> createRuleType(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> factory, BiConsumer<MinecraftServer, T> notifier) {
        return RuleTypeAccessor.invokeNew(argumentType, factory, notifier);
    }
}
