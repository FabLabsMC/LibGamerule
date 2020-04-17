package io.github.fablabsmc.fablabs.impl.gamerule;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.function.BiConsumer;
import java.util.function.Function;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

class EnumRuleType<E extends Enum<E>> extends LiteralRuleType<EnumRule<E>> {
	private final E[] supportedValues;

	EnumRuleType(Function<GameRules.RuleType<EnumRule<E>>, EnumRule<E>> ruleFactory, BiConsumer<MinecraftServer, EnumRule<E>> changeCallback, E[] supportedValues) {
		super(null, ruleFactory, changeCallback);
		this.supportedValues = supportedValues;
	}

	@Override
	public void register(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder, GameRules.RuleKey<EnumRule<E>> key) {
		LiteralCommandNode<ServerCommandSource> ruleNode = literal(key.getName()).build();

		for (E supportedValue : this.supportedValues) {
			ruleNode.addChild(
					literal(supportedValue.toString())
							.executes(context -> EnumRuleCommand.executeEnumSet(context, supportedValue.ordinal(), key))
							.build()
			);
		}

		literalArgumentBuilder.then(ruleNode);
	}
}
