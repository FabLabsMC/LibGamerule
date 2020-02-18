package com.martmists.libgamerule.rules;

import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.entities.ValueGetter;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class StringRule extends GameRules.Rule<StringRule> implements ValueGetter<String> {
    String value;

    public StringRule(GameRules.RuleType<StringRule> ruleType, String value) {
        super(ruleType);
        this.value = value;
    }

    public static GameRules.RuleType<StringRule> create(String defaultValue){
        return Gamerule.createRuleType(
                StringArgumentType::greedyString,
                (GameRules.RuleType<StringRule> t) -> new StringRule(t, defaultValue)
        );
    }

    @Override
    protected void setFromArgument(CommandContext<ServerCommandSource> commandContext, String string) {
        value = StringArgumentType.getString(commandContext, string);
    }

    @Override
    protected void deserialize(String string) {
        value = string;
    }

    @Override
    protected String serialize() {
        return value;
    }

    @Override
    public int getCommandResult() {
        return 0;
    }

    @Override
    protected StringRule getThis() {
        return this;
    }

    @Override
    public String get() {
        return value;
    }
}
