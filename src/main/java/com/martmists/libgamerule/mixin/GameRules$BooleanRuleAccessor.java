package com.martmists.libgamerule.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;

@Mixin(GameRules.BooleanRule.class)
public interface GameRules$BooleanRuleAccessor {
	@Invoker
	static GameRules.RuleType<GameRules.BooleanRule> invokeCreate(boolean initialValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> changeCallback) {
		throw new AssertionError("Untransformed accessor");
	}
}
