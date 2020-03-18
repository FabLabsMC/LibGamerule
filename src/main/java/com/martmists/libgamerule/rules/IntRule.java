package com.martmists.libgamerule.rules;

import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.entities.ValueGetter;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class IntRule extends GameRules.Rule<IntRule> implements ValueGetter<Integer> {
    // Not using GameRules.IntRule for flexibility in create()
    private int value;

    public IntRule(GameRules.RuleType<IntRule> ruleType, int value) {
        super(ruleType);
        this.value = value;
    }

    public static GameRules.RuleType<IntRule> create(int defaultValue) {
        return Gamerule.createRuleType(
                IntegerArgumentType::integer,
                (GameRules.RuleType<IntRule> t) -> new IntRule(t, defaultValue)
        );
    }

    public static GameRules.RuleType<IntRule> create(int defaultValue, int minValue) {
        return Gamerule.createRuleType(
                () -> IntegerArgumentType.integer(minValue),
                (GameRules.RuleType<IntRule> t) -> new IntRule(t, defaultValue)
        );
    }

    public static GameRules.RuleType<IntRule> create(int defaultValue, int minValue, int maxValue) {
        return Gamerule.createRuleType(
                () -> IntegerArgumentType.integer(minValue, maxValue),
                (GameRules.RuleType<IntRule> t) -> new IntRule(t, defaultValue)
        );
    }

    @Override
    protected void setFromArgument(CommandContext<ServerCommandSource> commandContext, String string) {
        value = IntegerArgumentType.getInteger(commandContext, string);
    }

    @Override
    protected void deserialize(String string) {
        value = Integer.parseInt(string);
    }

    @Override
    protected String serialize() {
        return Integer.toString(value);
    }

    @Override
    public int getCommandResult() {
        return 0;
    }

    @Override
    protected IntRule getThis() {
        return this;
    }

    @Override
    public Integer get() {
        return value;
    }
}
