package com.martmists.libgamerule.impl;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class LiteralRuleType<T extends GameRules.Rule<T>> extends GameRules.RuleType<T> {
	public LiteralRuleType(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> ruleFactory, BiConsumer<MinecraftServer, T> changeCallback) {
		super(argumentType, ruleFactory, changeCallback);
	}

	@Override
	@Deprecated
	public final RequiredArgumentBuilder<ServerCommandSource, ?> argument(String name) {
		return super.argument(name);
	}

	public abstract void register(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder, GameRules.RuleKey<T> key);
}
