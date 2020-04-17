package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.function.Supplier;

import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.impl.gamerule.GameRuleRegistryImpl;

import net.minecraft.world.GameRules;

public class EnumRule<E extends Enum<E>> extends LiteralRule<EnumRule<E>> implements Supplier<E> {
	private final Class<E> classType;
	private final E[] supportedValues;
	private E value;

	// TODO: i509VCB - Should we make these constructors private since people are not supposed to be able to invoke these, and then use some invokers to create these internally within the api?
	/**
	 * @deprecated Please use {@link RuleFactory} instead.
	 */
	@Deprecated
	public EnumRule(GameRules.RuleType<EnumRule<E>> type, E value, E[] supportedValues) {
		super(type);
		this.classType = value.getDeclaringClass();
		this.value = value;
		this.supportedValues = supportedValues;
	}

	// TODO: This should not be public, maybe use mixin to hide it?
	public void setValue(E value) throws IllegalArgumentException {
		checkNotNull(value);

		for (E supportedValue : this.supportedValues) {
			if (supportedValue.equals(value)) {
				this.value = value;
				return;
			}
		}

		throw new IllegalArgumentException("Tried to set an unsupported value: " + value.toString());
	}

	@Override
	protected void deserialize(String value) {
		int ordinal = parseInt(value);
		E[] possibleValues = this.classType.getEnumConstants();

		if (possibleValues.length <= ordinal + 1) { // Our ordinal doesn't exist, log the issue
			GameRuleRegistryImpl.LOGGER.warn("Failed to parse int {} for rule of type {}. Since it's ordinal is not present", ordinal, this.classType);
			return;
		}

		try {
			this.setValue(possibleValues[ordinal]);
		} catch (IllegalArgumentException e) { // Not a supported value
			GameRuleRegistryImpl.LOGGER.warn("Failed to parse int {} for rule of type {}. {}", ordinal, this.classType, e);
		}
	}

	private int parseInt(String string) {
		if (!string.isEmpty()) {
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException e) {
				GameRuleRegistryImpl.LOGGER.warn("Failed to parse int {} for rule of type {}", string, this.classType);
			}
		}

		return 0;
	}

	@Override
	protected String serialize() {
		return Integer.toString(this.value.ordinal());
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected EnumRule<E> getThis() {
		return this;
	}

	public Class<E> getEnumClass() {
		return this.classType;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	@Override
	public E get() {
		return this.value;
	}
}
