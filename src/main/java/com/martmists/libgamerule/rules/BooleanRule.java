package com.martmists.libgamerule.rules;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.entities.ValueGetter;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class BooleanRule extends GameRules.Rule<BooleanRule> implements ValueGetter<Boolean> {
    // Using this instead of GameRules.BooleanRule for ease of use
    boolean value;

    public BooleanRule(GameRules.RuleType<BooleanRule> ruleType, boolean value) {
        super(ruleType);
        this.value = value;
    }

    public static GameRules.RuleType<BooleanRule> create(boolean defaultValue) {
        return Gamerule.createRuleType(
                BoolArgumentType::bool,
                (GameRules.RuleType<BooleanRule> t) -> new BooleanRule(t, defaultValue)
        );
    }

    @Override
    protected void setFromArgument(CommandContext<ServerCommandSource> commandContext, String string) {
        value = BoolArgumentType.getBool(commandContext, string);
    }

    @Override
    protected void deserialize(String string) {
        value = string.equals("true");
    }

    @Override
    protected String serialize() {
        return Boolean.toString(value);
    }

    @Override
    public int getCommandResult() {
        return 0;
    }

    @Override
    protected BooleanRule getThis() {
        return this;
    }

    @Override
    public Boolean get() {
        return value;
    }
}
