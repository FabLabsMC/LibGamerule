package com.martmists.libgamerule.api.rule;

import com.martmists.libgamerule.api.RuleFactory;
import com.martmists.libgamerule.impl.GameRuleRegistryImpl;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

import java.util.function.DoubleSupplier;

public class DoubleRule extends GameRules.Rule<DoubleRule> implements DoubleSupplier {
	private double value;

	// TODO: i509VCB - Should we make these constructors private since people are not supposed to be able to invoke these, and then use some invokers to create these internally within the api?
	/**
	 * @deprecated Please use {@link RuleFactory} instead.
	 * @param type the rule type
	 */
	@Deprecated
	public DoubleRule(GameRules.RuleType<DoubleRule> type, double value) {
		super(type);
		this.value = value;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Double.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = DoubleRule.parseDouble(value);
	}

	private static double parseDouble(String string) {
		if (!string.isEmpty()) {
			try {
				return Double.parseDouble(string);
			} catch (NumberFormatException e) {
				GameRuleRegistryImpl.LOGGER.warn("Failed to parse double {}", string);
			}
		}

		return 0.0D;
	}

	@Override
	protected String serialize() {
		return Double.toString(this.value);
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected DoubleRule getThis() {
		return this;
	}

	@Override
	public double getAsDouble() {
		return this.value;
	}
}
