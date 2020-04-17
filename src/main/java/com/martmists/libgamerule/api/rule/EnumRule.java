package com.martmists.libgamerule.api.rule;

import com.martmists.libgamerule.api.RuleFactory;
import net.minecraft.world.GameRules;

import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

public class EnumRule<E extends Enum<E>> extends LiteralRule<EnumRule<E>> implements Supplier<E> {
	private final Class<E> classType;
	private final E[] supportedValues;
	private E value;

	// TODO: i509VCB - Should we make these constructors private since people are not supposed to be able to invoke these, and then use some invokers to create these internally within the api?
	/**
	 * @deprecated Please use {@link RuleFactory} instead.
	 * @param type the rule type
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
			if (supportedValue == value) {
				this.value = value;
				break;
			}
		}

		throw new IllegalArgumentException("Tried to set an unsupported value: " + value.toString());
	}

	@Override
	protected void deserialize(String value) {
		this.value = E.valueOf(this.classType, value);
	}

	@Override
	protected String serialize() {
		return this.value.toString();
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
	public E get() {
		return this.value;
	}
}
