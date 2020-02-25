package com.martmists.libgamerule;

import blue.endless.jankson.annotation.Nullable;
import com.martmists.libgamerule.entities.ValueGetter;
import com.martmists.libgamerule.mixin.GameRuleCommandAccessor;
import com.martmists.libgamerule.mixin.GameRulesAccessor;
import com.martmists.libgamerule.mixin.RuleTypeAccessor;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.tree.CommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Gamerule implements ModInitializer {
    public static Supplier<MinecraftServer> SERVER_SUPPLIER;
    public static boolean dirty = false;
    public static CommandDispatcher<ServerCommandSource> DISPATCHER;
    public static List<CommandNode<ServerCommandSource>> unregistered = new ArrayList<>();

    public static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String name, GameRules.RuleType<T> type) {
        dirty = true;
        GameRules.RuleKey<T> key = GameRulesAccessor.invokeRegister(name, type);

        CommandNode<ServerCommandSource> node = CommandManager.literal(name).executes((commandContext) -> {
            return GameRuleCommandAccessor.invokeExecuteQuery(commandContext.getSource(), key);
        }).then(type.argument("value").executes((commandContext) -> {
            return GameRuleCommandAccessor.invokeExecuteSet(commandContext, key);
        })).build();

        if (DISPATCHER == null){
            unregistered.add(node);
        } else {
            DISPATCHER.getRoot().getChild("gamerule").addChild(node);
        }
        return key;
    }

    @Nullable
    public static <T extends GameRules.Rule<T> & ValueGetter<V>, V> V get(GameRules.RuleKey<T> key) {
        return SERVER_SUPPLIER.get().getGameRules().get(key).get();
    }

    public static <T extends GameRules.Rule<T>> GameRules.RuleType<T> createRuleType(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> factory) {
        return createRuleType(argumentType, factory, (s, r) -> {
        });
    }

    public static <T extends GameRules.Rule<T>> GameRules.RuleType<T> createRuleType(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> factory, BiConsumer<MinecraftServer, T> notifier) {
        return RuleTypeAccessor.invokeNew(argumentType, factory, notifier);
    }

    @Override
    public void onInitialize() {
        CommandRegistry.INSTANCE.register(false, (dispatcher)-> {
            DISPATCHER = dispatcher;
            unregistered.forEach((n)->{
                DISPATCHER.getRoot().getChild("gamerule").addChild(n);
            });
            unregistered.clear();
        });
    }
}
