package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import com.mojang.brigadier.context.CommandContext;
import io.github.fablabsmc.fablabs.api.gamerule.v1.FloatSupplier;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.impl.gamerule.GameRuleRegistryImpl;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class FloatRule extends GameRules.Rule<FloatRule> implements FloatSupplier {
	private float value;

	// TODO: i509VCB - Should we make these constructors private since people are not supposed to be able to invoke these, and then use some invokers to create these internally within the api?

	/**
	 * @deprecated Please use {@link RuleFactory} instead.
	 */
	@Deprecated
	public FloatRule(GameRules.RuleType<FloatRule> type, float value) {
		super(type);
		this.value = value;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Float.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = FloatRule.parseFloat(value);
	}

	private static float parseFloat(String string) {
		if (!string.isEmpty()) {
			try {
				return Float.parseFloat(string);
			} catch (NumberFormatException e) {
				GameRuleRegistryImpl.LOGGER.warn("Failed to parse float {}", string);
			}
		}

		return 0.0F;
	}

	@Override
	protected String serialize() {
		return Float.toString(this.value);
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected FloatRule getThis() {
		return this;
	}

	@Override
	public float getAsFloat() {
		return this.value;
	}
}
