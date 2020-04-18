package io.github.fablabsmc.fablabs.mixin.gamerule;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.brigadier.arguments.ArgumentType;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

// i509VCB -> fixme: AW conflicts during compile due to mixin AP and loom: https://github.com/FabricMC/fabric-loom/issues/193
//@Mixin(GameRules.RuleType.class)
public interface GameRules$RuleTypeAccessor {
	// i509VCB -> fixme: AW conflicts during compile due to mixin AP and loom: https://github.com/FabricMC/fabric-loom/issues/193
	//@Invoker("<init>")
	static <T extends GameRules.Rule<T>> GameRules.RuleType<T> invokeNew(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> ruleFactory, BiConsumer<MinecraftServer, T> notifier) {
		throw new AssertionError("Untransformed accessor");
	}
}
