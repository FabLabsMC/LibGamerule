package com.martmists.libgamerule.rules;

import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.entities.ValueGetter;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class DoubleRule extends GameRules.Rule<DoubleRule> implements ValueGetter<Double> {
    private double value;

    public DoubleRule(GameRules.RuleType<DoubleRule> ruleType, double value) {
        super(ruleType);
        this.value = value;
    }

    public static GameRules.RuleType<DoubleRule> create(double defaultValue) {
        return Gamerule.createRuleType(
                DoubleArgumentType::doubleArg,
                (GameRules.RuleType<DoubleRule> t) -> new DoubleRule(t, defaultValue)
        );
    }

    public static GameRules.RuleType<DoubleRule> create(double defaultValue, double minValue) {
        return Gamerule.createRuleType(
                () -> DoubleArgumentType.doubleArg(minValue),
                (GameRules.RuleType<DoubleRule> t) -> new DoubleRule(t, defaultValue)
        );
    }

    public static GameRules.RuleType<DoubleRule> create(double defaultValue, double minValue, double maxValue) {
        return Gamerule.createRuleType(
                () -> DoubleArgumentType.doubleArg(minValue, maxValue),
                (GameRules.RuleType<DoubleRule> t) -> new DoubleRule(t, defaultValue)
        );
    }

    @Override
    protected void setFromArgument(CommandContext<ServerCommandSource> commandContext, String string) {
        value = DoubleArgumentType.getDouble(commandContext, string);
    }

    @Override
    protected void deserialize(String string) {
        value = Double.parseDouble(string);
    }

    @Override
    protected String serialize() {
        return Double.toString(value);
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
    public Double get() {
        return value;
    }
}
