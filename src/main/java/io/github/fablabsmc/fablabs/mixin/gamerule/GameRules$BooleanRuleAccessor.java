package io.github.fablabsmc.fablabs.mixin.gamerule;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

@Mixin(GameRules.BooleanRule.class)
public interface GameRules$BooleanRuleAccessor {
	@Invoker
	static GameRules.RuleType<GameRules.BooleanRule> invokeCreate(boolean initialValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> changeCallback) {
		throw new AssertionError("Untransformed accessor");
	}
}
