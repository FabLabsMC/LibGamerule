package com.martmists.libgamerule.impl;

import com.martmists.libgamerule.api.rule.EnumRule;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static net.minecraft.server.command.CommandManager.literal;

public class EnumRuleType<E extends Enum<E>> extends LiteralRuleType<EnumRule<E>> {
	private final E[] supportedValues;

	public EnumRuleType(Function<GameRules.RuleType<EnumRule<E>>, EnumRule<E>> ruleFactory, BiConsumer<MinecraftServer, EnumRule<E>> changeCallback, E[] supportedValues) {
		super(null, ruleFactory, changeCallback);
		this.supportedValues = supportedValues;
	}

	@Override
	public void register(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder, GameRules.RuleKey<EnumRule<E>> key) {
		LiteralCommandNode<ServerCommandSource> ruleNode = literal(key.getName()).build();

		for (E supportedValue : this.supportedValues) {
			ruleNode.addChild(
					literal(supportedValue.toString())
					.executes(context -> LiteralRuleCommand.executeEnumSet(context, supportedValue.toString(), key))
					.build()
			);
		}

		literalArgumentBuilder.then(ruleNode);
	}
}
