package io.github.fablabsmc.fablabs.mixin.gamerule;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

@Mixin(GameRules.IntRule.class)
public interface GameRules$IntRuleAccessor {
	@Invoker
	static GameRules.RuleType<GameRules.IntRule> invokeCreate(int initialValue, BiConsumer<MinecraftServer, GameRules.IntRule> changeCallback) {
		throw new AssertionError("Untransformed accessor");
	}
}
