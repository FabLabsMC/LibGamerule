package io.github.fablabsmc.fablabs.impl.gamerule;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.LiteralRule;
import io.github.fablabsmc.fablabs.mixin.gamerule.GameRuleCommandAccessor;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public final class LiteralRuleCommand {
	public static <T extends LiteralRule<T>> void register(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder, GameRules.RuleKey<T> key, LiteralRuleType<T> type) {
		literalArgumentBuilder.then(literal(key.getName())
				.executes(context -> {
					// We can use the vanilla query method
					return GameRuleCommandAccessor.invokeExecuteQuery(context.getSource(), key);
				}
		));

		// The LiteralRuleType handles the executeSet
		type.register(literalArgumentBuilder, key);
	}
}
