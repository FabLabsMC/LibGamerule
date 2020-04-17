package io.github.fablabsmc.fablabs.impl.gamerule;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.GameRules;

final class EnumRuleCommand {
	public static <E extends Enum<E>> int executeEnumSet(CommandContext<ServerCommandSource> context, int ordinal, GameRules.RuleKey<EnumRule<E>> key) throws CommandSyntaxException {
		// Mostly copied from vanilla, but tweaked so we can use literals
		ServerCommandSource serverCommandSource = context.getSource();
		EnumRule<E> rule = serverCommandSource.getMinecraftServer().getGameRules().get(key);

		rule.setValue(rule.getEnumClass().getEnumConstants()[ordinal]);
		serverCommandSource.sendFeedback(new TranslatableText("commands.gamerule.set", key.getName(), rule.toString()), true);
		return rule.getCommandResult();
	}
}
