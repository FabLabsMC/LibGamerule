package com.martmists.libgamerule.api;

import com.martmists.libgamerule.impl.GameRuleRegistryImpl;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public interface GameRuleRegistry {
	GameRuleRegistry INSTANCE = GameRuleRegistryImpl.INSTANCE;

	/**
	 * Registers a {@link GameRules.Rule}.
	 *
	 * @param id the id this rule will be named
	 * @param type the rule type
	 * @param <T> the type of rule
	 * @return a rule key which can be used to query the value of the rule
	 * @throws IllegalStateException if a rule of the same name already exists.
	 */
	<T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(Identifier id, GameRules.RuleType<T> type);
}
