package com.martmists.libgamerule.impl;

import com.martmists.libgamerule.api.rule.EnumRule;
import com.martmists.libgamerule.mixin.GameRuleCommandAccessor;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.GameRules;

import static net.minecraft.server.command.CommandManager.literal;

public final class LiteralRuleCommand {
	public static <E extends Enum<E>> int executeEnumSet(CommandContext<ServerCommandSource> context, String literal, GameRules.RuleKey<EnumRule<E>> key) throws CommandSyntaxException {
		// Mostly copied from vanilla, but tweaked so we can use literals
		ServerCommandSource serverCommandSource = context.getSource();
		EnumRule<E> rule = serverCommandSource.getMinecraftServer().getGameRules().get(key);
		E value;

		try {
			value = E.valueOf(rule.getEnumClass(), literal);
		} catch (Throwable t) {
			throw new SimpleCommandExceptionType(new LiteralText(t.getMessage())).create();
		}

		rule.setValue(value);
		serverCommandSource.sendFeedback(new TranslatableText("commands.gamerule.set", key.getName(), rule.toString()), true);
		return rule.getCommandResult();
	}

	public static <T extends GameRules.Rule<T>> void register(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder, GameRules.RuleKey<T> key, LiteralRuleType<T> type) {
		literalArgumentBuilder
				.then(literal(key.getName())
						.executes(context -> {
							// We can use the vanilla query method
							return GameRuleCommandAccessor.invokeExecuteQuery(context.getSource(), key);
						})
				);

		type.register(literalArgumentBuilder, key);
	}
}
