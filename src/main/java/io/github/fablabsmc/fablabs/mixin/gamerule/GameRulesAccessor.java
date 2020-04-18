package io.github.fablabsmc.fablabs.mixin.gamerule;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.GameRules;

@Mixin(GameRules.class)
public interface GameRulesAccessor {
	@Invoker
	static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> invokeRegister(String name, GameRules.RuleType<T> type) {
		throw new AssertionError("This shouldn't happen!");
	}
}
