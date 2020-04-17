package io.github.fablabsmc.fablabs.impl.gamerule.test;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.TextRule;

import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;

import net.fabricmc.api.ModInitializer;

public class GameRuleTest implements ModInitializer {
	public static final GameRules.RuleKey<EnumRule<Direction>> TEST_DIR = register("test_dir", RuleFactory.INSTANCE.createEnumRule(Direction.NORTH));
	public static final GameRules.RuleKey<GameRules.IntRule> TEST = register("test2", RuleFactory.INSTANCE.createBoundedIntType(0, 0));
	public static final GameRules.RuleKey<TextRule> TEXT = register("tesx", RuleFactory.INSTANCE.createTextType(new LiteralText("Yeet")));

	@Override
	public void onInitialize() {
	}

	private static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String id, GameRules.RuleType<T> type) {
		return GameRuleRegistry.INSTANCE.register(new Identifier("test", id), type);
	}
}
