package com.martmists.libgamerule.impl;

import com.martmists.libgamerule.api.GameRuleRegistry;
import com.martmists.libgamerule.mixin.GameRulesAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameRuleRegistryImpl implements GameRuleRegistry {
	public static final GameRuleRegistryImpl INSTANCE = new GameRuleRegistryImpl();
	public static final Logger LOGGER = LogManager.getLogger(GameRuleRegistry.class);

	private GameRuleRegistryImpl() {
	}

	@Override
	public <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(Identifier id, GameRules.RuleType<T> type) {
		return GameRulesAccessor.invokeRegister(id.toString(), type);
	}
}
