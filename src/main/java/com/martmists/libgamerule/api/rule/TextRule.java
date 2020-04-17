package com.martmists.libgamerule.api.rule;

import com.martmists.libgamerule.api.RuleFactory;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

import java.util.function.Supplier;

public class TextRule extends GameRules.Rule<TextRule> implements Supplier<Text> {
	private Text value;

	// TODO: i509VCB - Should we make these constructors private since people are not supposed to be able to invoke these, and then use some invokers to create these internally within the api?
	/**
	 * @deprecated Please use {@link RuleFactory} instead.
	 * @param type the rule type
	 * @param defaultValue
	 */
	@Deprecated
	public TextRule(GameRules.RuleType<TextRule> type, Text defaultValue) {
		super(type);
		this.value = defaultValue;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Text.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = Text.Serializer.fromJson(value);
	}

	@Override
	protected String serialize() {
		return Text.Serializer.toJson(this.value);
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected TextRule getThis() {
		return this;
	}

	@Override
	public Text get() {
		return this.value;
	}
}
