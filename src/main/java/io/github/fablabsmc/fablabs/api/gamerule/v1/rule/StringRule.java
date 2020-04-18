package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import java.util.function.Supplier;

import com.mojang.brigadier.context.CommandContext;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class StringRule extends GameRules.Rule<StringRule> implements Supplier<String> {
	private String value;

	// TODO: i509VCB - Should we make these constructors private since people are not supposed to be able to invoke these, and then use some invokers to create these internally within the api?

	/**
	 * @deprecated Please use {@link RuleFactory} instead.
	 */
	@Deprecated
	public StringRule(GameRules.RuleType<StringRule> type, String value) {
		super(type);
		this.value = value;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, String.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = value;
	}

	@Override
	protected String serialize() {
		return this.value;
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected StringRule getThis() {
		return this;
	}

	@Override
	public String get() {
		return this.value;
	}
}
