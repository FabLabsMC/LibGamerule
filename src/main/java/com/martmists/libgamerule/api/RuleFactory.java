package com.martmists.libgamerule.api;

import com.martmists.libgamerule.api.rule.DoubleRule;
import com.martmists.libgamerule.api.rule.EnumRule;
import com.martmists.libgamerule.api.rule.FloatRule;
import com.martmists.libgamerule.api.rule.StringRule;
import com.martmists.libgamerule.api.rule.TextRule;
import com.martmists.libgamerule.impl.RuleFactoryImpl;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

import java.util.function.BiConsumer;

public interface RuleFactory {
	RuleFactory INSTANCE = RuleFactoryImpl.INSTANCE;

	default GameRules.RuleType<GameRules.BooleanRule> createBooleanType(boolean defaultValue) {
		return this.createBooleanType(defaultValue, (server, rule) -> {
		});
	}

	GameRules.RuleType<GameRules.BooleanRule> createBooleanType(boolean defaultValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> notifier);

	default GameRules.RuleType<GameRules.IntRule> createIntType(int defaultValue) {
		return this.createIntType(defaultValue, (server, rule) -> {
		});
	}

	default GameRules.RuleType<GameRules.IntRule> createBoundedIntType(int defaultValue, int lowerBound) {
		return this.createBoundedIntType(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	default GameRules.RuleType<GameRules.IntRule> createBoundedIntType(int defaultValue, int lowerBound, BiConsumer<MinecraftServer, GameRules.IntRule> notifier) {
		return this.createBoundedIntType(defaultValue, lowerBound, Integer.MAX_VALUE, notifier);
	}

	default GameRules.RuleType<GameRules.IntRule> createBoundedIntType(int defaultValue, int lowerBound, int upperBound) {
		return this.createBoundedIntType(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	GameRules.RuleType<GameRules.IntRule> createIntType(int defaultValue, BiConsumer<MinecraftServer, GameRules.IntRule> notifier);

	GameRules.RuleType<GameRules.IntRule> createBoundedIntType(int defaultValue, int lowerBound, int upperBound, BiConsumer<MinecraftServer, GameRules.IntRule> notifier);

	default GameRules.RuleType<DoubleRule> createDoubleType(double defaultValue) {
		return this.createDoubleType(defaultValue, (server, rule) -> {
		});
	}

	default GameRules.RuleType<DoubleRule> createBoundedDoubleType(double defaultValue, double lowerBound) {
		return this.createBoundedDoubleType(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	default GameRules.RuleType<DoubleRule> createBoundedDoubleType(double defaultValue, double lowerBound, BiConsumer<MinecraftServer, DoubleRule> notifier) {
		return this.createBoundedDoubleType(defaultValue, lowerBound, Integer.MAX_VALUE, notifier);
	}

	default GameRules.RuleType<DoubleRule> createBoundedDoubleType(double defaultValue, double lowerBound, double upperBound) {
		return this.createBoundedDoubleType(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	GameRules.RuleType<DoubleRule> createDoubleType(double defaultValue, BiConsumer<MinecraftServer, DoubleRule> notifier);

	GameRules.RuleType<DoubleRule> createBoundedDoubleType(double defaultValue, double lowerBound, double upperBound, BiConsumer<MinecraftServer, DoubleRule> notifier);

	default GameRules.RuleType<FloatRule> createFloatType(float defaultValue) {
		return this.createFloatType(defaultValue, (server, rule) -> {
		});
	}

	default GameRules.RuleType<FloatRule> createBoundedFloatType(float defaultValue, float lowerBound) {
		return this.createBoundedFloatType(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	default GameRules.RuleType<FloatRule> createBoundedFloatType(float defaultValue, float lowerBound, BiConsumer<MinecraftServer, FloatRule> notifier) {
		return this.createBoundedFloatType(defaultValue, lowerBound, Integer.MAX_VALUE, notifier);
	}

	default GameRules.RuleType<FloatRule> createBoundedFloatType(float defaultValue, float lowerBound, float upperBound) {
		return this.createBoundedFloatType(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	GameRules.RuleType<FloatRule> createFloatType(float defaultValue, BiConsumer<MinecraftServer, FloatRule> notifier);

	GameRules.RuleType<FloatRule> createBoundedFloatType(float defaultValue, float lowerBound, float upperBound, BiConsumer<MinecraftServer, FloatRule> notifier);

	default GameRules.RuleType<TextRule> createTextType(Text defaultValue) {
		return this.createTextType(defaultValue, (server, rule) -> {
		});
	}

	GameRules.RuleType<TextRule> createTextType(Text defaultValue, BiConsumer<MinecraftServer, TextRule> notifier);

	default GameRules.RuleType<StringRule> createWordType(String defaultValue) {
		return this.createWordType(defaultValue, (server, rule) -> {
		});
	}

	GameRules.RuleType<StringRule> createWordType(String defaultValue, BiConsumer<MinecraftServer, StringRule> notifier);

	default GameRules.RuleType<StringRule> createStringType(String defaultValue) {
		return this.createStringType(defaultValue, (server, rule) -> {
		});
	}

	GameRules.RuleType<StringRule> createStringType(String defaultValue, BiConsumer<MinecraftServer, StringRule> notifier);

	default <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue) {
		return this.createEnumRule(defaultValue, (server, rule) -> {
		});
	}

	default <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, BiConsumer<MinecraftServer, EnumRule<E>> notifier) {
		return this.createEnumRule(defaultValue, defaultValue.getDeclaringClass().getEnumConstants(), notifier);
	}

	default <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues) {
		return this.createEnumRule(defaultValue, supportedValues, (server, rule) -> {
		});
	}

	<E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues, BiConsumer<MinecraftServer, EnumRule<E>> notifier);
}
