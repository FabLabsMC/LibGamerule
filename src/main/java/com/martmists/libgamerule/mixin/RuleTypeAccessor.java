package com.martmists.libgamerule.mixin;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mixin(GameRules.RuleType.class)
public interface RuleTypeAccessor {
    @Invoker("<init>")
    static <T extends GameRules.Rule<T>> GameRules.RuleType<T> invokeNew(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> ruleFactory, BiConsumer<MinecraftServer, T> notifier) {
        throw new AssertionError("Untransformed accessor");
    }
}
