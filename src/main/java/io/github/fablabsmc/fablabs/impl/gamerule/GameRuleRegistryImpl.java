package io.github.fablabsmc.fablabs.impl.gamerule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.mixin.gamerule.GameRulesAccessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

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
