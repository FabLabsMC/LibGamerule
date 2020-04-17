package com.martmists.libgamerule.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;

@Mixin(GameRules.IntRule.class)
public interface GameRules$IntRuleAccessor {
	@Invoker
	static GameRules.RuleType<GameRules.IntRule> invokeCreate(int initialValue, BiConsumer<MinecraftServer, GameRules.IntRule> changeCallback) {
		throw new AssertionError("Untransformed accessor");
	}
}
